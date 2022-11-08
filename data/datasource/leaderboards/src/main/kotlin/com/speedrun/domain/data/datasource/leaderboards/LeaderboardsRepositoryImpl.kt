package com.speedrun.domain.data.datasource.leaderboards

import com.speedrun.domain.core.wrapper.dispatchers.DispatcherProvider
import com.speedrun.domain.data.database.SpeedrunDatabase
import com.speedrun.domain.data.datasource.leaderboards.mapper.createId
import com.speedrun.domain.data.datasource.leaderboards.mapper.toLeaderboardEntity
import com.speedrun.domain.data.datasource.leaderboards.mapper.toLeaderboardModel
import com.speedrun.domain.data.datasource.leaderboards.mapper.toLeaderboardPlaceEntity
import com.speedrun.domain.data.datasource.players.mapper.*
import com.speedrun.domain.data.repo.leaderboards.LeaderboardsRepository
import com.speedrun.domain.data.repo.leaderboards.model.LeaderboardModel
import com.speedrun.domain.datasource.runs.mapper.toRunEntity
import com.speedrun.domain.datasource.runs.mapper.toRunPlayerEntity
import com.speedrun.domain.networking.api.leaderboards.LeaderboardsApiService
import com.speedrun.domain.networking.api.players.PolymorphicPlayerResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LeaderboardsRepositoryImpl @Inject constructor(
    private val leaderboardsApiService: LeaderboardsApiService,
    private val dispatcherProvider: DispatcherProvider,
    speedrunDatabase: SpeedrunDatabase,
) : LeaderboardsRepository {

    private val runDao = speedrunDatabase.runDao()
    private val runPlayerDao = speedrunDatabase.runPlayerDao()
    private val leaderboardDao = speedrunDatabase.leaderboardDao()
    private val leaderboardPlaceDao = speedrunDatabase.leaderboardPlaceDao()
    private val playerDao = speedrunDatabase.playerDao()
    private val userDao = speedrunDatabase.userDao()
    private val guestDao = speedrunDatabase.guestDao()
    private val countryDao = speedrunDatabase.countryDao()
    private val regionDao = speedrunDatabase.regionDao()

    override suspend fun refreshLeaderboards(gameId: String, categoryId: String) = withContext(dispatcherProvider.io()) {
        val response = leaderboardsApiService.getLeaderboard(gameId, categoryId)

        val leaderboardEntities = response.data.toLeaderboardEntity()
        val leaderboardPlaceEntities = response.data.runs.map { it.toLeaderboardPlaceEntity(response.data.createId()) }
        val userPlayerEntities = response.data.players.data.filterIsInstance<PolymorphicPlayerResponse.UserResponse>().map { it.toPlayerEntity() }
        val guestPlayerEntities = response.data.players.data.filterIsInstance<PolymorphicPlayerResponse.GuestResponse>().map { it.toPlayerEntity() }
        val userEntities = response.data.players.data.filterIsInstance<PolymorphicPlayerResponse.UserResponse>().map { it.toUserEntity() }
        val guestEntities = response.data.players.data.filterIsInstance<PolymorphicPlayerResponse.GuestResponse>().map { it.toGuestEntity() }
        val playerEntities = userPlayerEntities + guestPlayerEntities

        val countryEntities = response.data.players.data.filterIsInstance<PolymorphicPlayerResponse.UserResponse>().mapNotNull { it.location?.country?.toCountryEntity() }
        val regionEntities = response.data.players.data.filterIsInstance<PolymorphicPlayerResponse.UserResponse>().mapNotNull { it.location?.region?.toRegionEntity() }

        val runEntities = response.data.runs.map { it.run.toRunEntity() }
        val runPlayerEntities = response.data.runs.map { leaderboardRun -> leaderboardRun.run.players.map { it.toRunPlayerEntity(leaderboardRun.run.id) } }.flatten()

        leaderboardDao.upsert(leaderboardEntities)
        leaderboardPlaceDao.upsert(leaderboardPlaceEntities)
        countryDao.upsert(countryEntities)
        regionDao.upsert(regionEntities)
        playerDao.upsert(playerEntities)
        runPlayerDao.upsert(runPlayerEntities)

        userDao.upsert(userEntities)
        guestDao.upsert(guestEntities)
        runDao.upsert(runEntities)
    }

    override suspend fun getLeaderboard(gameId: String, categoryId: String): Flow<LeaderboardModel> = withContext(dispatcherProvider.io()) {
        leaderboardDao.getLeaderboard(gameId, categoryId).map { it.toLeaderboardModel() }
    }
}

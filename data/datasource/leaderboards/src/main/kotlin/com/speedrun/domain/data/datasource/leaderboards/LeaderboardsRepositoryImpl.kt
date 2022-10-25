package com.speedrun.domain.data.datasource.leaderboards

import com.speedrun.domain.core.wrapper.dispatchers.DispatcherProvider
import com.speedrun.domain.data.database.SpeedrunDatabase
import com.speedrun.domain.data.datasource.leaderboards.mapper.toLeaderboardModel
import com.speedrun.domain.data.datasource.players.mapper.toGuestEntity
import com.speedrun.domain.data.datasource.players.mapper.toPlayerEntity
import com.speedrun.domain.data.datasource.players.mapper.toUserEntity
import com.speedrun.domain.data.repo.leaderboards.LeaderboardsRepository
import com.speedrun.domain.data.repo.leaderboards.model.LeaderboardModel
import com.speedrun.domain.networking.api.leaderboards.LeaderboardsApiService
import com.speedrun.domain.networking.api.players.PolymorphicPlayerResponse
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LeaderboardsRepositoryImpl @Inject constructor(
    private val leaderboardsApiService: LeaderboardsApiService,
    private val dispatcherProvider: DispatcherProvider,
    speedrunDatabase: SpeedrunDatabase,
) : LeaderboardsRepository {

    private val playerDao = speedrunDatabase.playerDao()
    private val userDao = speedrunDatabase.userDao()
    private val guestDao = speedrunDatabase.guestDao()

    override suspend fun getLeaderboard(gameId: String, categoryId: String): LeaderboardModel = withContext(dispatcherProvider.io()) {
            val response = leaderboardsApiService.getLeaderboard(gameId, categoryId)

            val userPlayerEntities = response.data.players.data.filterIsInstance<PolymorphicPlayerResponse.UserResponse>().map { it.toPlayerEntity() }
            val guestPlayerEntities = response.data.players.data.filterIsInstance<PolymorphicPlayerResponse.GuestResponse>().map { it.toPlayerEntity() }
            val userEntities = response.data.players.data.filterIsInstance<PolymorphicPlayerResponse.UserResponse>().map { it.toUserEntity() }
            val guestEntities = response.data.players.data.filterIsInstance<PolymorphicPlayerResponse.GuestResponse>().map { it.toGuestEntity() }

            val playerEntities = userPlayerEntities + guestPlayerEntities
            playerDao.upsert(playerEntities)
            userDao.upsert(userEntities)
            guestDao.upsert(guestEntities)

            response.data.toLeaderboardModel()
        }
}

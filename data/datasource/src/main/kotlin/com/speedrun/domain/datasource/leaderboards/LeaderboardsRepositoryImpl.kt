package com.speedrun.domain.data.datasource.leaderboards

import com.speedrun.domain.api.leaderboards.LeaderboardsApiService
import com.speedrun.domain.api.players.models.PolymorphicPlayerResponse
import com.speedrun.domain.core.wrapper.dispatchers.DispatcherProvider
import com.speedrun.domain.data.database.SpeedrunDatabase
import com.speedrun.domain.datasource.players.mapper.toGuestEntity
import com.speedrun.domain.datasource.players.mapper.toPlayerEntity
import com.speedrun.domain.datasource.players.mapper.toUserEntity
import com.speedrun.domain.datasource.runs.mapper.toEntity
import com.speedrun.domain.datasource.runs.mapper.toRunEntity
import com.speedrun.domain.datasource.runs.mapper.toRunPlayerEntity
import com.speedrun.domain.datasource.categories.mapper.toEntity
import com.speedrun.domain.datasource.leaderboards.mapper.*
import com.speedrun.domain.repo.leaderboards.LeaderboardsRepository
import com.speedrun.domain.repo.leaderboards.model.LeaderboardModel
import com.speedrun.domain.repo.leaderboards.model.LeaderboardPlaceModel
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

    private val categoryDao = speedrunDatabase.categoryDao()
    private val guestDao = speedrunDatabase.guestDao()
    private val leaderboardDao = speedrunDatabase.leaderboardDao()
    private val leaderboardPlaceDao = speedrunDatabase.leaderboardPlaceDao()
    private val placeDao = speedrunDatabase.placeDao()
    private val playerDao = speedrunDatabase.playerDao()
    private val runDao = speedrunDatabase.runDao()
    private val runPlayerDao = speedrunDatabase.runPlayerDao()
    private val runValueDao = speedrunDatabase.runValueDao()
    private val userDao = speedrunDatabase.userDao()
    private val valueDao  = speedrunDatabase.valueDao()
    private val variableDao  = speedrunDatabase.variableDao()
    private val variableValueDao = speedrunDatabase.variableValueDao()
    private val videoDao = speedrunDatabase.videoDao()

    override suspend fun refreshLeaderboards(gameId: String, categoryId: String) = withContext(dispatcherProvider.io()) {
        val response = leaderboardsApiService.getLeaderboard(gameId, categoryId)

        val leaderboardEntities = response.data.toLeaderboardEntity()
        val placeEntities = response.data.runs.map { it.toPlaceEntity() }
        val leaderboardPlaceEntities = response.data.runs.map { it.toLeaderboardPlaceEntity(response.data.createId()) }
        val userPlayerEntities = response.data.players.data.filterIsInstance<PolymorphicPlayerResponse.UserResponse>().map { it.toPlayerEntity() }
        val guestPlayerEntities = response.data.players.data.filterIsInstance<PolymorphicPlayerResponse.GuestResponse>().map { it.toPlayerEntity() }
        val userEntities = response.data.players.data.filterIsInstance<PolymorphicPlayerResponse.UserResponse>().map { it.toUserEntity() }
        val guestEntities = response.data.players.data.filterIsInstance<PolymorphicPlayerResponse.GuestResponse>().map { it.toGuestEntity() }
        val playerEntities = userPlayerEntities + guestPlayerEntities

        val runEntities = response.data.runs.map { it.run.toRunEntity() }
        val runPlayerEntities = response.data.runs.map { leaderboardRun -> leaderboardRun.run.players.map { it.toRunPlayerEntity(leaderboardRun.run.id) } }.flatten()
        val runValueEntities = response.data.runs.mapNotNull { it.run.toRunValueEntities() }.flatten()
        val runCategoryEntity = response.data.category.data.toEntity(gameId)
        val runVideoEntities =  response.data.runs.mapNotNull { it.run.videos?.toEntity(it.run.id) }.flatten()
        val variableEntities = response.data.variables.data.map { it.toVariableEntity() }
        val valueEntities = response.data.variables.data.map { it.toValueEntities() }.flatten()
        val variableValueEntities = response.data.variables.data.map { it.toVariableValueEntity() }.flatten()

        categoryDao.upsert(runCategoryEntity)
        leaderboardDao.upsert(leaderboardEntities)
        placeDao.upsert(placeEntities)
        runDao.upsert(runEntities)
        videoDao.upsert(runVideoEntities)
        playerDao.upsert(playerEntities)
        userDao.upsert(userEntities)
        guestDao.upsert(guestEntities)

        leaderboardPlaceDao.upsert(leaderboardPlaceEntities)
        runPlayerDao.upsert(runPlayerEntities)
        runValueDao.upsert(runValueEntities)
        valueDao.upsert(valueEntities)
        variableDao.upsert(variableEntities)
        variableValueDao.upsert(variableValueEntities)
    }

    override suspend fun observeLeaderboard(gameId: String, categoryId: String): Flow<LeaderboardModel> = withContext(dispatcherProvider.io()) {
        leaderboardDao.getLeaderboard(gameId, categoryId).map { it.toLeaderboardModel() }
    }

    override suspend fun observeLeaderboardPlace(runId: String): Flow<LeaderboardPlaceModel> = withContext(dispatcherProvider.io()) {
        leaderboardDao.getLeaderboardPlace(runId).map { it.leaderboardRunEntity.toLeaderboardPlaceModel(it.runResult, it.placeEntity) }
    }
}

package com.speedrun.domain.data.datasource.players

import com.speedrun.domain.core.wrapper.dispatchers.DispatcherProvider
import com.speedrun.domain.data.database.SpeedrunDatabase
import com.speedrun.domain.data.datasource.players.mapper.*
import com.speedrun.domain.data.pagination.PaginationModel
import com.speedrun.domain.data.repo.players.PlayersRepository
import com.speedrun.domain.data.repo.players.model.PlayerModel
import com.speedrun.domain.networking.api.players.PlayersApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayersRepositoryImpl @Inject constructor(
    private val playersApiService: PlayersApiService,
    private val dispatcherProvider: DispatcherProvider,
    speedrunDatabase: SpeedrunDatabase,
) : PlayersRepository {

    private val playerDao = speedrunDatabase.playerDao()
    private val userDao = speedrunDatabase.userDao()
    private val locationDao = speedrunDatabase.locationDao()
    private val userLocationDao = speedrunDatabase.userLocationDao()

    override suspend fun refreshPlayer(playerId: String) = withContext(dispatcherProvider.io()) {
        val player = playersApiService.getPlayer(playerId)
        val playerEntity = player.userResponse.toPlayerEntity()
        val userEntity = player.userResponse.toUserEntity()
        playerDao.upsert(playerEntity)
        userDao.upsert(userEntity)
    }

    override suspend fun searchPlayers(
        name: String,
        offset: Int,
        max: Int
    ): PaginationModel<PlayerModel.UserModel> = withContext(dispatcherProvider.io()) {

        val searchedPlayers =
            playersApiService.searchPlayers(name = name, offset = offset, max = max)

        val playerEntities = searchedPlayers.data.map { it.toPlayerEntity() }
        val userEntities = searchedPlayers.data.map { it.toUserEntity() }
        val locations = searchedPlayers.data.mapNotNull { it.location?.toLocationEntity() }
        val userLocations =
            searchedPlayers.data.mapNotNull { it.location?.toUserLocationEntity(it.id) }

        playerDao.upsert(playerEntities)
        userDao.upsert(userEntities)
        locationDao.upsert(locations)
        userLocationDao.upsert(userLocations)

        searchedPlayers.toPaginationModel()
    }

    override suspend fun observePlayer(playerId: String): Flow<PlayerModel.UserModel?> = withContext(dispatcherProvider.io()) {
        playerDao.observePlayer(playerId).map { it.userResult?.toUserModel()}
    }
}
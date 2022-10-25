package com.speedrun.domain.data.datasource.players

import com.speedrun.domain.core.wrapper.dispatchers.DispatcherProvider
import com.speedrun.domain.data.database.SpeedrunDatabase
import com.speedrun.domain.data.datasource.players.mapper.toPaginationModel
import com.speedrun.domain.data.datasource.players.mapper.toPlayerEntity
import com.speedrun.domain.data.datasource.players.mapper.toUserEntity
import com.speedrun.domain.data.pagination.PaginationModel
import com.speedrun.domain.data.repo.players.PlayersRepository
import com.speedrun.domain.data.repo.players.model.PlayerModel
import com.speedrun.domain.networking.api.players.PlayersApiService
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayersRepositoryImpl @Inject constructor(
    private val playersApiService: PlayersApiService,
    private val dispatcherProvider: DispatcherProvider,
    speedrunDatabase: SpeedrunDatabase,
) : PlayersRepository {

    val playerDao = speedrunDatabase.playerDao()
    val userDao = speedrunDatabase.userDao()

    override suspend fun searchPlayers(
        name: String,
        offset: Int,
        max: Int
    ): PaginationModel<PlayerModel.UserModel> = withContext(dispatcherProvider.io()) {

        val searchedPlayers = playersApiService.searchPlayers(name = name, offset = offset, max = max)

        val playerEntities = searchedPlayers.data.map { it.toPlayerEntity() }
        val userEntities = searchedPlayers.data.map { it.toUserEntity() }

        playerDao.upsert(playerEntities)
        userDao.upsert(userEntities)

        searchedPlayers.toPaginationModel()
    }
}
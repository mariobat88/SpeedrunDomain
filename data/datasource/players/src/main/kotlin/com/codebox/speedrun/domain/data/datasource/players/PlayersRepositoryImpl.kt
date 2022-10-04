package com.codebox.speedrun.domain.data.datasource.players

import com.codebox.speedrun.domain.data.datasource.players.mapper.toModel
import com.codebox.speedrun.domain.data.pagination.PaginationModel
import com.codebox.speedrun.domain.data.repo.players.PlayersRepository
import com.codebox.speedrun.domain.data.repo.players.model.PlayerModel
import com.codebox.speedrun.domain.networking.api.players.PlayersApiService
import com.codebox.speedrun.domain.wrapper.dispatchers.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayersRepositoryImpl @Inject constructor(
    private val playersApiService: PlayersApiService,
    //speedrunDatabase: SpeedrunDatabase,
    private val dispatcherProvider: DispatcherProvider,
) : PlayersRepository {

    //val gameDao = speedrunDatabase.gameDao()

    override suspend fun searchPlayers(
        name: String,
        offset: Int,
        max: Int
    ): PaginationModel<PlayerModel.UserModel> = withContext(dispatcherProvider.io()) {
        val searchedPlayers = playersApiService.searchPlayers(name = name, offset = offset, max = max)
//        val entities = searchedGames.data.map { it.toEntity() }
//        gameDao.upsert(entities)

        searchedPlayers.toModel()
    }
}
package com.codebox.speedrun.domain.data.datasource.games

import com.codebox.speedrun.domain.data.database.SpeedrunDatabase
import com.codebox.speedrun.domain.data.datasource.games.mapper.toEntity
import com.codebox.speedrun.domain.data.datasource.games.mapper.toModel
import com.codebox.speedrun.domain.data.pagination.PaginationModel
import com.codebox.speedrun.domain.data.repo.games.GamesRepository
import com.codebox.speedrun.domain.data.repo.games.model.GameModel
import com.codebox.speedrun.domain.networking.api.games.GamesApiService
import com.codebox.speedrun.domain.wrapper.dispatchers.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GamesRepositoryImpl @Inject constructor(
    private val gamesApiService: GamesApiService,
    speedrunDatabase: SpeedrunDatabase,
    private val dispatcherProvider: DispatcherProvider,
) : GamesRepository {

    val gameDao = speedrunDatabase.gameDao()

    override suspend fun searchGames(
        name: String,
        offset: Int,
        max: Int
    ): PaginationModel<GameModel> = withContext(dispatcherProvider.io()) {
        val searchedGames = gamesApiService.searchGames(name = name, offset = offset, max = max)
        val entities = searchedGames.data.map { it.toEntity() }
        gameDao.upsert(entities)

        searchedGames.toModel()
    }
}
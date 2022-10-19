package com.speedrun.domain.data.datasource.games

import com.speedrun.domain.core.wrapper.dispatchers.DispatcherProvider
import com.speedrun.domain.data.database.SpeedrunDatabase
import com.speedrun.domain.data.datasource.games.mapper.*
import com.speedrun.domain.data.pagination.PaginationModel
import com.speedrun.domain.data.repo.games.GamesRepository
import com.speedrun.domain.data.repo.games.model.GameModel
import com.speedrun.domain.networking.api.games.GamesApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GamesRepositoryImpl @Inject constructor(
    private val gamesApiService: GamesApiService,
    private val dispatcherProvider: DispatcherProvider,
    speedrunDatabase: SpeedrunDatabase,
) : GamesRepository {

    private val gameDao = speedrunDatabase.gameDao()
    private val runTimeDao = speedrunDatabase.runTimeDao()
    private val gameRunTimeDao = speedrunDatabase.gameRunTimeDao()

    override suspend fun searchGames(
        name: String,
        offset: Int,
        max: Int
    ): PaginationModel<GameModel> = withContext(dispatcherProvider.io()) {
        val searchedGames = gamesApiService.searchGames(name = name, offset = offset, max = max)

        val runTimeEntities = searchedGames.data.map { it.ruleset.runTimes }
            .asSequence()
            .flatten()
            .distinctBy { it.jsonValue }
            .map { it.toRunTimeEntity() }
            .toList()

        val gameEntities = searchedGames.data.map { it.toGameEntity() }

        val gameRunTimeEntities = searchedGames.data.map { it.toGameRunTimeEntity() }.flatten()

        runTimeDao.upsert(runTimeEntities)
        gameDao.upsert(gameEntities)
        gameRunTimeDao.upsert(gameRunTimeEntities)

        searchedGames.toModel()
    }

    override suspend fun getGameById(id: String): Flow<GameModel> =
        withContext(dispatcherProvider.io()) {
            gameDao.getGameById(id).map { it.toGameModel() }
        }
}
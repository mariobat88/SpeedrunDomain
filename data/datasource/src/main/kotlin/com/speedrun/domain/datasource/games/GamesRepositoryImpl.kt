package com.speedrun.domain.datasource.games

import com.speedrun.domain.api.games.GamesApiService
import com.speedrun.domain.core.wrapper.dispatchers.DispatcherProvider
import com.speedrun.domain.data.database.SpeedrunDatabase
import com.speedrun.domain.datasource.games.mapper.*
import com.speedrun.domain.datasource.platforms.mapper.toPlatformEntity
import com.speedrun.domain.repo.games.GamesRepository
import com.speedrun.domain.repo.games.model.GameModel
import com.speedrun.domain.repo.pagination.model.PaginationModel
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
    private val gameDeveloperDao = speedrunDatabase.gameDeveloperDao()
    private val platformDao = speedrunDatabase.platformDao()

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
        val gameDeveloperEntities = searchedGames.data.map { it.toGameDeveloperEntity() }.flatten()
        val platformEntities = searchedGames.data.map { it.platforms.data.map { it.toPlatformEntity() } }.flatten()

        runTimeDao.upsert(runTimeEntities)
        gameDao.upsert(gameEntities)
        gameRunTimeDao.upsert(gameRunTimeEntities)
        gameDeveloperDao.upsert(gameDeveloperEntities)
        platformDao.upsert(platformEntities)

        searchedGames.toModel()
    }

    override suspend fun getGameById(id: String): Flow<GameModel> =
        withContext(dispatcherProvider.io()) {
            gameDao.getGameById(id).map { it.toGameModel() }
        }
}
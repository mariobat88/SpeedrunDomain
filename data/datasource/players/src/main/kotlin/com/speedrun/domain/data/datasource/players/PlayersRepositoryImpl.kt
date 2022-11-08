package com.speedrun.domain.data.datasource.players

import com.speedrun.domain.core.wrapper.dispatchers.DispatcherProvider
import com.speedrun.domain.data.database.SpeedrunDatabase
import com.speedrun.domain.data.datasource.players.mapper.*
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

    private val playerDao = speedrunDatabase.playerDao()
    private val userDao = speedrunDatabase.userDao()
    private val countryDao = speedrunDatabase.countryDao()
    private val regionDao = speedrunDatabase.regionDao()

    override suspend fun searchPlayers(
        name: String,
        offset: Int,
        max: Int
    ): PaginationModel<PlayerModel.UserModel> = withContext(dispatcherProvider.io()) {

        val searchedPlayers = playersApiService.searchPlayers(name = name, offset = offset, max = max)

        val playerEntities = searchedPlayers.data.map { it.toPlayerEntity() }
        val userEntities = searchedPlayers.data.map { it.toUserEntity() }
        val countryEntities = searchedPlayers.data.mapNotNull { it.location?.country?.toCountryEntity() }
        val regionEntities = searchedPlayers.data.mapNotNull { it.location?.region?.toRegionEntity() }

        countryDao.upsert(countryEntities)
        regionDao.upsert(regionEntities)
        playerDao.upsert(playerEntities)
        userDao.upsert(userEntities)

        searchedPlayers.toPaginationModel()
    }
}
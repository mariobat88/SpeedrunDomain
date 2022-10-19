package com.speedrun.domain.data.repo.games

import com.speedrun.domain.data.pagination.PaginationModel
import com.speedrun.domain.data.repo.games.model.GameModel
import kotlinx.coroutines.flow.Flow

interface GamesRepository {
    suspend fun searchGames(name: String, offset: Int, max: Int): PaginationModel<GameModel>
    suspend fun getGameById(id: String): Flow<GameModel>
}

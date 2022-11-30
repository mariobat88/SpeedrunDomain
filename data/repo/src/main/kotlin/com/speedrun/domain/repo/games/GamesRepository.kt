package com.speedrun.domain.repo.games

import com.speedrun.domain.repo.games.model.GameModel
import com.speedrun.domain.repo.pagination.model.PaginationModel
import kotlinx.coroutines.flow.Flow

interface GamesRepository {
    suspend fun searchGames(name: String, offset: Int, max: Int): PaginationModel<GameModel>
    suspend fun getGameById(id: String): Flow<GameModel>
}

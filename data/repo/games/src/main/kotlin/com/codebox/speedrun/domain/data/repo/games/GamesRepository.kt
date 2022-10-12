package com.codebox.speedrun.domain.data.repo.games

import com.codebox.speedrun.domain.data.pagination.PaginationModel
import com.codebox.speedrun.domain.data.repo.games.model.GameModel
import kotlinx.coroutines.flow.Flow

interface GamesRepository {
    suspend fun searchGames(name: String, offset: Int, max: Int): PaginationModel<GameModel>
    suspend fun getGameById(id: String): Flow<GameModel>
}

package com.codebox.speedrun.domain.data.repo.games

import com.codebox.speedrun.domain.data.pagination.PaginationModel
import com.codebox.speedrun.domain.data.repo.games.model.GameModel

interface GamesRepository {
    suspend fun searchGame(name: String, offset: Int, max: Int): PaginationModel<GameModel>
}

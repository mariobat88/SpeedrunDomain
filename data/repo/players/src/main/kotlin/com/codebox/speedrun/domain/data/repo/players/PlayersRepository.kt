package com.codebox.speedrun.domain.data.repo.players

import com.codebox.speedrun.domain.data.pagination.PaginationModel
import com.codebox.speedrun.domain.data.repo.players.model.PlayerModel

interface PlayersRepository {
    suspend fun searchPlayers(name: String, offset: Int, max: Int): PaginationModel<PlayerModel>
}

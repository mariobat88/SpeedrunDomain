package com.speedrun.domain.data.repo.players

import com.speedrun.domain.data.pagination.PaginationModel
import com.speedrun.domain.data.repo.players.model.PlayerModel

interface PlayersRepository {
    suspend fun searchPlayers(name: String, offset: Int, max: Int): PaginationModel<PlayerModel.UserModel>
}

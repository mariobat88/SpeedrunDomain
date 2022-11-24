package com.speedrun.domain.data.repo.players

import com.speedrun.domain.data.pagination.PaginationModel
import com.speedrun.domain.data.repo.players.model.PlayerModel
import kotlinx.coroutines.flow.Flow

interface PlayersRepository {
    suspend fun refreshPlayer(playerId:String)
    suspend fun searchPlayers(name: String, offset: Int, max: Int): PaginationModel<PlayerModel.UserModel>
    suspend fun observePlayer(playerId:String): Flow<PlayerModel.UserModel>
}

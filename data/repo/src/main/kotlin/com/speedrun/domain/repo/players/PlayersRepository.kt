package com.speedrun.domain.repo.players

import com.speedrun.domain.repo.pagination.model.PaginationModel
import com.speedrun.domain.repo.players.model.PlayerModel
import kotlinx.coroutines.flow.Flow

interface PlayersRepository {
    suspend fun refreshPlayer(playerId:String)
    suspend fun refreshUserPersonalBests(playerId:String)
    suspend fun searchPlayers(name: String, offset: Int, max: Int): PaginationModel<PlayerModel.UserModel>
    suspend fun observePlayer(playerId:String): Flow<PlayerModel?>
}

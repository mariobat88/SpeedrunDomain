package com.speedrun.domain.feature.run

import com.speedrun.domain.core.framework.async.Async
import com.speedrun.domain.core.framework.async.Loading
import com.speedrun.domain.repo.leaderboards.model.LeaderboardPlaceModel
import com.speedrun.domain.repo.players.model.PlayerModel

sealed class Intent {
    data class PlayerClicked(val playerId: String) : Intent()
}

data class ViewState(
    val youtubeApiKey: String,
    val leaderboardPlaceAsync: Async<LeaderboardPlaceModel> = Loading(),
    val examinerAsync: Async<PlayerModel.UserModel?> = Loading(),
    val verifyDate: String? = null
)
package com.speedrun.domain.feature.run

import com.speedrun.domain.core.framework.async.Async
import com.speedrun.domain.core.framework.async.Loading
import com.speedrun.domain.data.repo.leaderboards.model.LeaderboardPlaceModel

sealed class Intent {

}

data class ViewState(
    val leaderboardPlaceAsync: Async<LeaderboardPlaceModel> = Loading()
)
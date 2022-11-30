package com.speedrun.domain.feature.game

import com.speedrun.domain.core.framework.async.Async
import com.speedrun.domain.core.framework.async.Uninitialized
import com.speedrun.domain.repo.developers.model.DeveloperModel
import com.speedrun.domain.repo.games.model.GameModel
import com.speedrun.domain.repo.publishers.model.PublisherModel

sealed class Intent{
    object BackClicked : Intent()
    object LeaderboardsClicked : Intent()
}

data class ViewState(
    val gameAsync: Async<GameModel> = Uninitialized,
    val developersAsync: Async<List<DeveloperModel>?> = Uninitialized,
    val publishersAsync: Async<List<PublisherModel>?> = Uninitialized,
    val developers: String = "",
    val publishers: String = "",
)

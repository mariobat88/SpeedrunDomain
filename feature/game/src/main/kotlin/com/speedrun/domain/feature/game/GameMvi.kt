package com.speedrun.domain.feature.game

import com.speedrun.domain.core.framework.async.Async
import com.speedrun.domain.core.framework.async.Uninitialized
import com.speedrun.domain.data.repo.developers.model.DeveloperModel
import com.speedrun.domain.data.repo.games.model.GameModel
import com.speedrun.domain.data.repo.publishers.model.PublisherModel
import com.speedrun.domain.repo.runs.model.RunModel

sealed class Intent{
    object LeaderboardsClicked : Intent()
}

data class ViewState(
    val gameAsync: Async<GameModel> = Uninitialized,
    val developersAsync: Async<List<DeveloperModel>?> = Uninitialized,
    val publishersAsync: Async<List<PublisherModel>?> = Uninitialized,
    val runsAsync: Async<List<RunModel>> = Uninitialized,
    val developers: String = "",
    val publishers: String = "",
)

package com.speedrun.domain.feature.game

import com.speedrun.domain.core.framework.async.Async
import com.speedrun.domain.core.framework.async.Uninitialized
import com.speedrun.domain.data.repo.developers.model.DeveloperModel
import com.speedrun.domain.data.repo.games.model.GameModel

sealed class Intent

data class ViewState(
    val gameAsync: Async<GameModel> = Uninitialized,
    val developersAsync: Async<List<DeveloperModel>?> = Uninitialized,
)

package com.codebox.speedrun.domain.dashboard

import com.codebox.speedrun.domain.repo.runs.model.RunModel
import com.codebox.speedrun.domain.data.repo.games.model.GameModel

sealed class Intent

data class ViewState(
    val latestRuns: List<LatestRun> = emptyList()
)

data class LatestRun(
    val game: GameModel,
    val runs: List<RunModel>
)

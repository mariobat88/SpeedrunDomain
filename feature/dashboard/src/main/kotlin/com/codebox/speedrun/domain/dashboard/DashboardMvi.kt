package com.codebox.speedrun.domain.dashboard

import com.codebox.speedrun.domain.repo.runs.model.RunModel

sealed class Intent

data class ViewState(
    val latestRuns: List<RunModel> = emptyList()
)
package com.speedrun.domain.repo.leaderboards.model

import com.speedrun.domain.repo.runs.model.RunModel

data class LeaderboardPlaceModel(
    val place: Int,
    val run: RunModel?,
)

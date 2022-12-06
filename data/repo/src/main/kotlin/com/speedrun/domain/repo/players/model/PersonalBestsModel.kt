package com.speedrun.domain.repo.players.model

import com.speedrun.domain.repo.runs.model.RunModel

data class RunPositionModel(
    val place: Int?,
    val runModel: RunModel,
)

package com.speedrun.domain.datasource.runs.mapper

import com.speedrun.domain.api.players.models.FlatPlayerResponse
import com.speedrun.domain.data.database.entities.RunPlayerEntity

fun FlatPlayerResponse.toRunPlayerEntity(runId: String) = RunPlayerEntity(
    runId = runId,
    playerId = id ?: name!!,
)

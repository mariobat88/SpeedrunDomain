package com.speedrun.domain.datasource.runs.mapper

import com.speedrun.domain.data.database.entities.RunPlayerEntity
import com.speedrun.domain.networking.api.players.FlatPlayerResponse

fun FlatPlayerResponse.toRunPlayerEntity(runId: String) = RunPlayerEntity(
    runId = runId,
    playerId = id,
)

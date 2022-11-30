package com.speedrun.domain.datasource.leaderboards.mapper

import com.speedrun.domain.api.runs.models.FlatRunResponse
import com.speedrun.domain.data.database.entities.RunValueEntity

fun FlatRunResponse.toRunValueEntities(): List<RunValueEntity>? {
    return values?.mapNotNull {
        RunValueEntity(
            runId = id,
            variableId = it.key,
            valueId = it.value
        )
    }
}
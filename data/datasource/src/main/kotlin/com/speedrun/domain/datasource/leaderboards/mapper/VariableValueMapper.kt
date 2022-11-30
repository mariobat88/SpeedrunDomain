package com.speedrun.domain.datasource.leaderboards.mapper

import com.speedrun.domain.api.leaderboards.models.VariablesResponse
import com.speedrun.domain.data.database.entities.VariableValueEntity

fun VariablesResponse.toVariableValueEntity(): List<VariableValueEntity> {
    return values.values.map {
        VariableValueEntity(
            variableId = id,
            valueId = it.key
        )
    }
}

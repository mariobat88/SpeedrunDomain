package com.speedrun.domain.data.datasource.leaderboards.mapper

import com.speedrun.domain.data.database.entities.VariableValueEntity
import com.speedrun.domain.networking.api.leaderboards.VariablesResponse

fun VariablesResponse.toVariableValueEntity(): List<VariableValueEntity> {
    return values.values.entries.map {
        VariableValueEntity(
            id = it.key,
            label = it.value.label,
            variableId = id,
        )
    }
}

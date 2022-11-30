package com.speedrun.domain.datasource.leaderboards.mapper

import com.speedrun.domain.api.leaderboards.models.VariablesResponse
import com.speedrun.domain.data.database.entities.ValueEntity

fun VariablesResponse.toValueEntities() : List<ValueEntity>{
    return values.values.map {
        ValueEntity(
            id = it.key,
            label = it.value.label
        )
    }
}
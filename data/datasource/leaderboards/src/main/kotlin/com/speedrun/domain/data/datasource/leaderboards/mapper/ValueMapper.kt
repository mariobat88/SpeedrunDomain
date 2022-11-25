package com.speedrun.domain.data.datasource.leaderboards.mapper

import com.speedrun.domain.data.database.entities.ValueEntity
import com.speedrun.domain.networking.api.leaderboards.VariablesResponse

fun VariablesResponse.toValueEntities() : List<ValueEntity>{
    return values.values.map {
        ValueEntity(
            id = it.key,
            label = it.value.label
        )
    }
}
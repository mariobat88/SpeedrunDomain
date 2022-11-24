package com.speedrun.domain.data.datasource.leaderboards.mapper

import com.speedrun.domain.data.database.entities.VariableEntity
import com.speedrun.domain.networking.api.leaderboards.VariablesResponse

fun VariablesResponse.toVariableEntity() = VariableEntity(
    id = id,
    name = name,
    category = category,
    scope = scope.type,
    mandatory = mandatory,
    userDefined = userDefined,
    obsoletes = obsoletes,
    isSubcategory = isSubcategory,
)

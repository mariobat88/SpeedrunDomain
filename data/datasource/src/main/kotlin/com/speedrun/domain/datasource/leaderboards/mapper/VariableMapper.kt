package com.speedrun.domain.datasource.leaderboards.mapper

import com.speedrun.domain.api.leaderboards.models.VariablesResponse
import com.speedrun.domain.data.database.entities.VariableEntity

fun VariablesResponse.toVariableEntity() = VariableEntity(
    id = id,
    name = name,
    categoryId = category,
    scope = scope.type,
    mandatory = mandatory,
    userDefined = userDefined,
    obsoletes = obsoletes,
    isSubcategory = isSubcategory,
)

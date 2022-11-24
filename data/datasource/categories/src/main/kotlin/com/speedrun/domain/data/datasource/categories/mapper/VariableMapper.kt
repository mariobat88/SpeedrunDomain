package com.speedrun.domain.data.datasource.categories.mapper

import com.speedrun.domain.data.database.entities.VariableEntity
import com.speedrun.domain.data.repo.categories.model.VariableModel

fun VariableEntity.toVariableModel() = VariableModel(
    id = id,
    name = name,
    category = category,
    scope = scope,
    mandatory = mandatory,
    userDefined = userDefined,
    obsoletes = obsoletes,
    isSubcategory = isSubcategory,
)

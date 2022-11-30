package com.speedrun.domain.datasource.categories.mapper

import com.speedrun.domain.data.database.entities.ValueEntity
import com.speedrun.domain.repo.categories.model.VariableModel

fun ValueEntity.toValueModel() = VariableModel.Value(
    id = id,
    label = label
)

package com.speedrun.domain.data.datasource.categories.mapper

import com.speedrun.domain.data.database.entities.ValueEntity
import com.speedrun.domain.data.repo.categories.model.VariableModel

fun ValueEntity.toValueModel() = VariableModel.Value(
    id = id,
    label = label
)

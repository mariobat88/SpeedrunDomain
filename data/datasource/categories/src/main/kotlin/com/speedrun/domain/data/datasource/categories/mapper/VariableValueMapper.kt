package com.speedrun.domain.data.datasource.categories.mapper

import com.speedrun.domain.data.database.entities.VariableValueEntity
import com.speedrun.domain.data.repo.categories.model.VariableModel

fun VariableValueEntity.toVariableValueModel() = VariableModel.Value(
    id = id,
    label = label
)

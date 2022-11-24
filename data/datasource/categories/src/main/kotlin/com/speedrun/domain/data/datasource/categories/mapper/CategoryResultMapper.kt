package com.speedrun.domain.data.datasource.categories.mapper

import com.speedrun.domain.data.database.result.CategoryResult

fun CategoryResult.toCategoryModel() = categoryEntity?.toModel(variableEntities)

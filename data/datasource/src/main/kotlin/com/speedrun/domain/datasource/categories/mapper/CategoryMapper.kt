package com.speedrun.domain.datasource.categories.mapper

import com.speedrun.domain.api.categories.models.CategoryResponse
import com.speedrun.domain.data.database.entities.CategoryEntity
import com.speedrun.domain.data.database.result.VariableResult
import com.speedrun.domain.repo.categories.model.CategoryModel

fun CategoryResponse.toEntity(gameId: String) = CategoryEntity(
    id = id,
    miscellaneous = miscellaneous,
    name = name,
    players = players.toEntity(),
    rules = rules,
    type = type,
    weblink = weblink,
    gameId = gameId,
)

private fun CategoryResponse.Players.toEntity() = CategoryEntity.Players(
    type = type,
    value = value,
)

fun CategoryEntity.toModel(
    variableEntities: List<VariableResult> = emptyList()
) = CategoryModel(
    id = id,
    links = emptyList(),
    miscellaneous = miscellaneous,
    name = name,
    players = players.toModel(),
    rules = rules,
    type = type,
    weblink = weblink,
    variables = variableEntities.map { it.toVariableModel() }
)

fun CategoryEntity.Players.toModel() = CategoryModel.Players(
    type = type,
    value = value,
)

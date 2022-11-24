package com.speedrun.domain.data.datasource.categories.mapper

import com.speedrun.domain.data.database.entities.CategoryEntity
import com.speedrun.domain.data.database.entities.VariableEntity
import com.speedrun.domain.data.repo.categories.model.CategoryModel
import com.speedrun.domain.networking.api.categories.CategoryResponse

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
    variableEntities: List<VariableEntity> = emptyList()
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

package com.speedrun.domain.data.datasource.categories.mapper

import com.speedrun.domain.data.database.entities.CategoryEntity
import com.speedrun.domain.data.datasource.common.mapper.toModel
import com.speedrun.domain.data.repo.categories.model.CategoryModel
import com.speedrun.domain.networking.api.categories.CategoryData

fun CategoryData.toEntity() = CategoryEntity(
    id = id,
    miscellaneous = miscellaneous,
    name = name,
    players = players.toEntity(),
    rules = rules,
    type = type,
    weblink = weblink,
)

private fun CategoryData.Players.toEntity() = CategoryEntity.Players(
    type = type,
    value = value,
)

fun CategoryEntity.toModel() = CategoryModel(
    id = id,
    links = emptyList(),
    miscellaneous = miscellaneous,
    name = name,
    players = players.toModel(),
    rules = rules,
    type = type,
    weblink = weblink,
)

fun CategoryEntity.Players.toModel() = CategoryModel.Players(
    type = type,
    value = value,
)

fun CategoryData.toModel() = CategoryModel(
    id = id,
    links = links.map { it.toModel() },
    miscellaneous = miscellaneous,
    name = name,
    players = players.toModel(),
    rules = rules,
    type = type,
    weblink = weblink,
)

fun CategoryData.Players.toModel() = CategoryModel.Players(
    type = type,
    value = value,
)

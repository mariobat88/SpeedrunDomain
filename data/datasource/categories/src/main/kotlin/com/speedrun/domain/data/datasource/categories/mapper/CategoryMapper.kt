package com.speedrun.domain.data.datasource.categories.mapper

import com.speedrun.domain.data.datasource.common.mapper.toModel
import com.speedrun.domain.data.repo.categories.model.CategoryModel
import com.speedrun.domain.networking.api.categories.CategoryResponse

fun CategoryResponse.Data.toModel() = CategoryModel(
    id = id,
    links = links.map { it.toModel() },
    miscellaneous = miscellaneous,
    name = name,
    players = players.toModel(),
    rules = rules,
    type = type,
    weblink = weblink,
)

fun CategoryResponse.Data.Players.toModel() = CategoryModel.Players(
    type = type,
    value = value,
)

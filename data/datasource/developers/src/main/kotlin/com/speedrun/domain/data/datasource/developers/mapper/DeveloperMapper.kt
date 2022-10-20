package com.speedrun.domain.data.datasource.developers.mapper

import com.speedrun.domain.data.database.entities.DeveloperEntity
import com.speedrun.domain.data.repo.developers.model.DeveloperModel
import com.speedrun.domain.networking.api.developers.DeveloperResponse

fun DeveloperResponse.Data.toDeveloperEntity() = DeveloperEntity(
    id = id,
    name = name,
)

fun DeveloperEntity.toDeveloperModel() = DeveloperModel(
    id = id,
    name = name,
    links = emptyList()
)

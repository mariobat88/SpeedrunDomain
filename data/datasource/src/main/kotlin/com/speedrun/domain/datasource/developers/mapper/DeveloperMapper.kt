package com.speedrun.domain.datasource.developers.mapper

import com.speedrun.domain.api.developers.models.DeveloperResponse
import com.speedrun.domain.data.database.entities.DeveloperEntity
import com.speedrun.domain.repo.developers.model.DeveloperModel

fun DeveloperResponse.Data.toDeveloperEntity() = DeveloperEntity(
    id = id,
    name = name,
)

fun DeveloperEntity.toDeveloperModel() = DeveloperModel(
    id = id,
    name = name,
    links = emptyList()
)

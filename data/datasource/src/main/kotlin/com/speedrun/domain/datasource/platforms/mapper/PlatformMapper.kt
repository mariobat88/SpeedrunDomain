package com.speedrun.domain.datasource.platforms.mapper

import com.speedrun.domain.api.platforms.models.PlatformResponse
import com.speedrun.domain.data.database.entities.PlatformEntity
import com.speedrun.domain.repo.platforms.model.PlatformModel

fun PlatformResponse.toPlatformEntity() = PlatformEntity(
    id = id,
    name = name,
    released = released,
)

fun PlatformEntity.toPlatformModel() = PlatformModel(
    id = id,
    name = name,
    released = released,
)

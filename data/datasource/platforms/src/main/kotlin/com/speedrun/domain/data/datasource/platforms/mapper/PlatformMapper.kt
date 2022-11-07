package com.speedrun.domain.data.datasource.platforms.mapper

import com.speedrun.domain.data.database.entities.PlatformEntity
import com.speedrun.domain.data.repo.platforms.model.PlatformModel
import com.speedrun.domain.networking.api.platforms.PlatformResponse

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

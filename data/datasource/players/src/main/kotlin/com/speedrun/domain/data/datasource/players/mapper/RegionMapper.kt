package com.speedrun.domain.data.datasource.players.mapper

import com.speedrun.domain.data.database.entities.RegionEntity
import com.speedrun.domain.networking.api.players.PolymorphicPlayerResponse

fun PolymorphicPlayerResponse.UserResponse.Location.Region.toRegionEntity() = RegionEntity(
    code = code,
    names = names.toNamesEntity()
)

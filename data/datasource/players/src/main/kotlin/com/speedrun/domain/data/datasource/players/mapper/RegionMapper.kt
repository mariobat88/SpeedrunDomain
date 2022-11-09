package com.speedrun.domain.data.datasource.players.mapper

import com.speedrun.domain.data.database.entities.RegionEntity
import com.speedrun.domain.data.datasource.common.mapper.toNamesEntity
import com.speedrun.domain.data.datasource.common.mapper.toNamesModel
import com.speedrun.domain.data.repo.players.model.RegionModel
import com.speedrun.domain.networking.api.players.PolymorphicPlayerResponse

fun PolymorphicPlayerResponse.UserResponse.Location.Region.toRegionEntity() = RegionEntity(
    code = code,
    names = names.toNamesEntity()
)

fun PolymorphicPlayerResponse.UserResponse.Location.Region.toRegionModel() = RegionModel(
    code = code,
    names = names.toNamesModel()
)

fun RegionEntity.toRegionModel() = RegionModel(
    code = code,
    names = names.toNamesModel()
)

package com.speedrun.domain.datasource.players.mapper

import com.speedrun.domain.api.players.models.PolymorphicPlayerResponse
import com.speedrun.domain.data.database.entities.LocationEntity
import com.speedrun.domain.data.database.entities.UserLocationEntity
import com.speedrun.domain.repo.players.model.PlayerModel

fun PolymorphicPlayerResponse.UserResponse.Location.createLocationId() =
    "${country.code}${region?.code}"

fun PolymorphicPlayerResponse.UserResponse.Location.toLocationEntity() = LocationEntity(
    id = createLocationId(),
    country = country.toLocationEntity(),
    region = region?.toLocationEntity()
)

private fun PolymorphicPlayerResponse.UserResponse.Location.Country.toLocationEntity() =
    LocationEntity.Country(
        code = code,
        international = names.international,
        japanese = names.japanese,
    )

private fun PolymorphicPlayerResponse.UserResponse.Location.Region.toLocationEntity() =
    LocationEntity.Region(
        code = code,
        international = names.international,
        japanese = names.japanese,
    )

fun PolymorphicPlayerResponse.UserResponse.Location.toUserLocationEntity(userId: String) =
    UserLocationEntity(
        userId = userId,
        locationId = createLocationId(),
    )

fun LocationEntity.toModel() = PlayerModel.UserModel.Location(
    country = country.toModel(),
    region = region?.toModel()
)

private fun LocationEntity.Country.toModel() = PlayerModel.UserModel.Location.Country(
    code = code,
    international = international,
    japanese = japanese,
)

private fun LocationEntity.Region.toModel() = PlayerModel.UserModel.Location.Region(
    code = code,
    international = international,
    japanese = japanese,
)

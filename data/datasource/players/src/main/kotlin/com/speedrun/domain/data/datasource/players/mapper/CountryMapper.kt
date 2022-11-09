package com.speedrun.domain.data.datasource.players.mapper

import com.speedrun.domain.data.database.entities.CountryEntity
import com.speedrun.domain.data.datasource.common.mapper.toNamesEntity
import com.speedrun.domain.data.datasource.common.mapper.toNamesModel
import com.speedrun.domain.data.repo.players.model.CountryModel
import com.speedrun.domain.networking.api.players.PolymorphicPlayerResponse

fun PolymorphicPlayerResponse.UserResponse.Location.Country.toCountryEntity() = CountryEntity(
    code = code,
    names = names.toNamesEntity()
)

fun PolymorphicPlayerResponse.UserResponse.Location.Country.toCountryModel() = CountryModel(
    code = code,
    names = names.toNamesModel()
)

fun CountryEntity.toCountryModel() = CountryModel(
    code = code,
    names = names.toNamesModel()
)

package com.speedrun.domain.data.datasource.players.mapper

import com.speedrun.domain.data.database.entities.UserEntity
import com.speedrun.domain.data.repo.players.model.NamesModel
import com.speedrun.domain.networking.api.players.NamesResponse

fun NamesResponse.toModel()  = NamesModel(
    international = international,
    japanese = japanese
)

fun NamesResponse.toNamesEntity()  = UserEntity.Names(
    international = international,
    japanese = japanese
)

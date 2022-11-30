package com.speedrun.domain.datasource.players.mapper

import com.speedrun.domain.api.players.models.NamesResponse
import com.speedrun.domain.data.database.entities.UserEntity
import com.speedrun.domain.repo.players.model.NamesModel

fun NamesResponse.toModel()  = NamesModel(
    international = international,
    japanese = japanese
)

fun NamesResponse.toNamesEntity()  = UserEntity.Names(
    international = international,
    japanese = japanese
)

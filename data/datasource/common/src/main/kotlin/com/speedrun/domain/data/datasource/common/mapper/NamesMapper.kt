package com.speedrun.domain.data.datasource.common.mapper

import com.speedrun.domain.data.database.entities.embeds.Names
import com.speedrun.domain.data.repo.common.model.NamesModel
import com.speedrun.domain.networking.api.common.NamesResponse

fun NamesResponse.toNamesModel()  = NamesModel(
    international = international,
    japanese = japanese
)

fun NamesResponse.toNamesEntity()  = Names(
    international = international,
    japanese = japanese
)

fun Names.toNamesModel()  = NamesModel(
    international = international,
    japanese = japanese
)

package com.speedrun.domain.datasource.games.mapper

import com.speedrun.domain.api.games.models.FlatGameResponse
import com.speedrun.domain.data.database.entities.GameEntity

fun FlatGameResponse.toEntity() = GameEntity(
    id = id,
    names = names.toEntity(),
    boostReceived = boostReceived,
    boostDistinctDonors = boostDistinctDonors,
    abbreviation = abbreviation,
    weblink = weblink,
    discord = discord,
    released = released,
    releaseDate = releaseDate,
    ruleset = ruleset.toEntity(),
    romhack = romhack,
    created = created,
    assets = assets.toEntity(),
)

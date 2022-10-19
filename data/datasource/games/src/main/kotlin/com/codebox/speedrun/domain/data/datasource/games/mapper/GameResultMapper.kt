package com.codebox.speedrun.domain.data.datasource.games.mapper

import com.codebox.speedrun.domain.data.database.result.GameEntityResult
import com.codebox.speedrun.domain.data.repo.games.model.GameModel

fun GameEntityResult.toGameModel(): GameModel {
    return GameModel(
        id = gameEntity.id,
        names = gameEntity.names.toGameEntity(),
        boostReceived = gameEntity.boostReceived,
        boostDistinctDonors = gameEntity.boostDistinctDonors,
        abbreviation = gameEntity.abbreviation,
        weblink = gameEntity.weblink,
        discord = gameEntity.discord,
        released = gameEntity.released,
        releaseDate = gameEntity.releaseDate,
        ruleset = gameEntity.ruleset.toGameEntity(runTimes?.map { it.toRunTimeEnum() }),
        romhack = gameEntity.romhack,
        gametypes = emptyList(),
        platforms = emptyList(),
        regions = emptyList(),
        genres = emptyList(),
        engines = emptyList(),
        developers = emptyList(),
        publishers = emptyList(),
        moderators = emptyMap(),
        created = gameEntity.created,
        assets = gameEntity.assets.toGameEntity(),
        links = emptyList(),
    )
}

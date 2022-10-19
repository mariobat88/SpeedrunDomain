package com.speedrun.domain.data.datasource.games.mapper

import com.speedrun.domain.data.database.entities.GameRunTimeEntity
import com.speedrun.domain.networking.api.games.GameResponse

fun GameResponse.toGameRunTimeEntity() : List<GameRunTimeEntity>{
    return ruleset.runTimes.map { GameRunTimeEntity(this.id, it) }
}

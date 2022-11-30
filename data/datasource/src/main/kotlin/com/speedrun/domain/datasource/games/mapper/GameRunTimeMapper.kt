package com.speedrun.domain.datasource.games.mapper

import com.speedrun.domain.api.games.models.GameResponse
import com.speedrun.domain.data.database.entities.GameRunTimeEntity

fun GameResponse.toGameRunTimeEntity() : List<GameRunTimeEntity>{
    return ruleset.runTimes.map { GameRunTimeEntity(this.id, it) }
}

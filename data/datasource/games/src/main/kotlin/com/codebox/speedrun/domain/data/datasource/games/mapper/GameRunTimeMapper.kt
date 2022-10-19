package com.codebox.speedrun.domain.data.datasource.games.mapper

import com.codebox.speedrun.domain.data.database.entities.GameRunTimeEntity
import com.codebox.speedrun.domain.networking.api.games.GameResponse

fun GameResponse.toGameRunTimeEntity() : List<GameRunTimeEntity>{
    return ruleset.runTimes.map { GameRunTimeEntity(this.id, it) }
}

package com.speedrun.domain.data.datasource.games.mapper

import com.speedrun.domain.data.database.entities.GameDeveloperEntity
import com.speedrun.domain.data.database.entities.GameRunTimeEntity
import com.speedrun.domain.networking.api.games.GameResponse

fun GameResponse.toGameDeveloperEntity() : List<GameDeveloperEntity>{
    return developers.map { GameDeveloperEntity(this.id, it) }
}

package com.speedrun.domain.datasource.games.mapper

import com.speedrun.domain.api.games.models.GameResponse
import com.speedrun.domain.data.database.entities.GameDeveloperEntity

fun GameResponse.toGameDeveloperEntity() : List<GameDeveloperEntity>{
    return developers.map { GameDeveloperEntity(this.id, it) }
}

package com.speedrun.domain.datasource.games.mapper

import com.speedrun.domain.api.games.models.Names
import com.speedrun.domain.data.database.entities.GameEntity
import com.speedrun.domain.repo.games.model.GameModel

fun Names.toEntity() = GameEntity.Names(
    international = international,
    japanese = japanese,
    twitch = twitch,
)

fun Names.toModel() = GameModel.Names(
    international = international,
    japanese = japanese,
    twitch = twitch,
)

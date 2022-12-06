package com.speedrun.domain.datasource.games.mapper

import com.speedrun.domain.api.games.models.Ruleset
import com.speedrun.domain.data.database.entities.GameEntity
import com.speedrun.domain.repo.games.model.GameModel

fun Ruleset.toEntity() = GameEntity.Ruleset(
    showMilliseconds = showMilliseconds,
    requireVerification = requireVerification,
    requireVideo = requireVideo,
    defaultTime = defaultTime,
    emulatorsAllowed = emulatorsAllowed,
)

fun Ruleset.toModel() = GameModel.Ruleset(
    showMilliseconds = showMilliseconds,
    requireVerification = requireVerification,
    requireVideo = requireVideo,
    runTimes = runTimes,
    defaultTime = defaultTime,
    emulatorsAllowed = emulatorsAllowed,
)

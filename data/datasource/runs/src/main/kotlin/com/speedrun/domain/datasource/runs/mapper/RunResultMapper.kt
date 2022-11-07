package com.speedrun.domain.datasource.runs.mapper

import com.speedrun.domain.data.database.result.RunEntityResult
import com.speedrun.domain.repo.runs.model.RunModel

fun RunEntityResult.toRunModel(): RunModel {
    return run.toRunModel(game, platform, runPlayers)
}

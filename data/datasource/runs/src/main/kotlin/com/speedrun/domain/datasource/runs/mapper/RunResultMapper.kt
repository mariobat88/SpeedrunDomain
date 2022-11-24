package com.speedrun.domain.datasource.runs.mapper

import com.speedrun.domain.data.database.result.RunResult
import com.speedrun.domain.repo.runs.model.RunModel

fun RunResult.toRunModel(): RunModel {
    return run.toRunModel(game, categoryResult, platform, runPlayers, videos)
}

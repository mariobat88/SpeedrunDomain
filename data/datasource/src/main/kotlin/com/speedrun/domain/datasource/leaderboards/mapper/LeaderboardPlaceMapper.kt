package com.speedrun.domain.datasource.leaderboards.mapper

import com.speedrun.domain.data.database.entities.LeaderboardPlaceEntity
import com.speedrun.domain.data.database.result.RunResult
import com.speedrun.domain.datasource.runs.mapper.toRunModel
import com.speedrun.domain.repo.leaderboards.model.LeaderboardPlaceModel

fun LeaderboardPlaceEntity.toLeaderboardPlaceModel(
    runResult: RunResult?
) = LeaderboardPlaceModel(
    place = place,
    run = runResult?.toRunModel(),
)

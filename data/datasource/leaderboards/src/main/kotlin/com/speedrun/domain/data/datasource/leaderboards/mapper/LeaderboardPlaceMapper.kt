package com.speedrun.domain.data.datasource.leaderboards.mapper

import com.speedrun.domain.data.database.entities.LeaderboardPlaceEntity
import com.speedrun.domain.data.database.result.RunResult
import com.speedrun.domain.data.repo.leaderboards.model.LeaderboardPlaceModel
import com.speedrun.domain.datasource.runs.mapper.toRunModel

fun LeaderboardPlaceEntity.toLeaderboardPlaceModel(
    runResult: RunResult?
) = LeaderboardPlaceModel(
    place = place,
    run = runResult?.toRunModel(),
)

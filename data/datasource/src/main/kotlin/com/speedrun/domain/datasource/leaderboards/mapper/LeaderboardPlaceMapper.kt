package com.speedrun.domain.datasource.leaderboards.mapper

import com.speedrun.domain.data.database.entities.LeaderboardRunEntity
import com.speedrun.domain.data.database.entities.PlaceEntity
import com.speedrun.domain.data.database.result.RunResult
import com.speedrun.domain.datasource.runs.mapper.toRunModel
import com.speedrun.domain.repo.leaderboards.model.LeaderboardPlaceModel

fun LeaderboardRunEntity.toLeaderboardPlaceModel(
    runResult: RunResult?,
    place: PlaceEntity?,
) = LeaderboardPlaceModel(
    place = place?.place,
    run = runResult?.toRunModel(),
)

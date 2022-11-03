package com.speedrun.domain.data.datasource.leaderboards.mapper

import com.speedrun.domain.data.database.entities.LeaderboardPlaceEntity
import com.speedrun.domain.data.database.result.RunEntityResult
import com.speedrun.domain.data.repo.leaderboards.model.LeaderboardPlaceModel
import com.speedrun.domain.datasource.runs.mapper.toRunModel

fun LeaderboardPlaceEntity.toLeaderboardPlaceModel(
    runEntityResult: RunEntityResult?
) = LeaderboardPlaceModel(
    place = place,
    run = runEntityResult?.toRunModel(),
)

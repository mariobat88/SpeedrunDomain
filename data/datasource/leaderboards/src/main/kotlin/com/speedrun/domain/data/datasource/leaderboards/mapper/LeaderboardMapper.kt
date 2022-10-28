package com.speedrun.domain.data.datasource.leaderboards.mapper

import com.speedrun.domain.data.database.entities.LeaderboardEntity
import com.speedrun.domain.data.database.entities.LeaderboardPlaceEntity
import com.speedrun.domain.data.database.result.LeaderboardEntityResult
import com.speedrun.domain.data.database.result.LeaderboardPlaceEntityResult
import com.speedrun.domain.data.repo.leaderboards.model.LeaderboardModel
import com.speedrun.domain.networking.api.leaderboards.LeaderboardResponse

fun LeaderboardResponse.Data.createId() = "$game$category"

fun LeaderboardResponse.Data.toLeaderboardEntity() = LeaderboardEntity(
    id = createId(),
    weblink = weblink,
    game = game,
    category = category,
    level = level,
    platform = platform,
    region = region,
    emulators = emulators,
    videoOnly = videoOnly,
    timing = timing,
)

fun LeaderboardResponse.Data.LeaderboardRun.toLeaderboardPlaceEntity(
    leaderboardId: String
) = LeaderboardPlaceEntity(
    leaderboardId = leaderboardId,
    runId = run.id,
    place = place,
)

fun LeaderboardEntityResult.toLeaderboardModel(): LeaderboardModel {
    return leaderboardEntity.toLeaderboardModel(leaderboardPlaces)
}

fun LeaderboardEntity.toLeaderboardModel(
    leaderboardPlacesResult: List<LeaderboardPlaceEntityResult>
) = LeaderboardModel(
    id = id,
    weblink = weblink,
    game = null,
    category = category,
    level = level,
    platform = platform,
    region = region,
    emulators = emulators,
    videoOnly = videoOnly,
    timing = timing,
    runs = leaderboardPlacesResult.map { it.leaderboardPlaceEntity.toLeaderboardPlaceModel(it.runEntityResult?.runEntity) },
    links = emptyList(),
)

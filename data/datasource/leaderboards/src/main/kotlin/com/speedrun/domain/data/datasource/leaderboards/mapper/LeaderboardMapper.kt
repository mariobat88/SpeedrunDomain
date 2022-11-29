package com.speedrun.domain.data.datasource.leaderboards.mapper

import com.speedrun.domain.data.database.entities.LeaderboardEntity
import com.speedrun.domain.data.database.entities.LeaderboardPlaceEntity
import com.speedrun.domain.data.database.result.LeaderboardPlaceResult
import com.speedrun.domain.data.database.result.LeaderboardResult
import com.speedrun.domain.data.repo.leaderboards.model.LeaderboardModel
import com.speedrun.domain.networking.api.leaderboards.LeaderboardResponse

fun LeaderboardResponse.Data.createId() = "$game$category"

fun LeaderboardResponse.Data.toLeaderboardEntity() = LeaderboardEntity(
    id = createId(),
    weblink = weblink,
    gameId = game,
    categoryId = category.data.id,
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

fun LeaderboardResult.toLeaderboardModel(): LeaderboardModel {
    return leaderboardEntity.toLeaderboardModel(leaderboardPlaces)
}

fun LeaderboardEntity.toLeaderboardModel(
    leaderboardPlacesResult: List<LeaderboardPlaceResult>
) = LeaderboardModel(
    id = id,
    weblink = weblink,
    game = null,
    category = categoryId,
    level = level,
    platform = platform,
    region = region,
    emulators = emulators,
    videoOnly = videoOnly,
    timing = timing,
    runs = leaderboardPlacesResult.map { it.leaderboardPlaceEntity.toLeaderboardPlaceModel(it.runResult) },
    links = emptyList(),
)

package com.speedrun.domain.data.datasource.leaderboards.mapper

import com.speedrun.domain.data.database.entities.GameEntity
import com.speedrun.domain.data.database.entities.LeaderboardEntity
import com.speedrun.domain.data.database.entities.LeaderboardPlaceEntity
import com.speedrun.domain.data.database.entities.RunEntity
import com.speedrun.domain.data.database.result.LeaderboardEntityResult
import com.speedrun.domain.data.datasource.games.mapper.toModel
import com.speedrun.domain.data.repo.leaderboards.model.LeaderboardModel
import com.speedrun.domain.datasource.runs.mapper.toRunModel
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
    return leaderboardEntity.toLeaderboardModel(gameEntity, runs)
}

fun LeaderboardEntity.toLeaderboardModel(
    gameEntity: GameEntity?,
    runEntities: List<RunEntity>?
) = LeaderboardModel(
    id = id,
    weblink = weblink,
    game = gameEntity?.toModel(),
    category = category,
    level = level,
    platform = platform,
    region = region,
    emulators = emulators,
    videoOnly = videoOnly,
    timing = timing,
    runs = runEntities?.map { it.toRunModel() },
    links = emptyList(),
)

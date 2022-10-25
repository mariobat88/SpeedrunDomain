package com.speedrun.domain.data.datasource.leaderboards.mapper

import com.speedrun.domain.data.datasource.common.mapper.toModel
import com.speedrun.domain.data.repo.leaderboards.model.LeaderboardModel
import com.speedrun.domain.datasource.runs.mapper.toModel
import com.speedrun.domain.networking.api.leaderboards.LeaderboardResponse

fun LeaderboardResponse.Data.toLeaderboardModel() = LeaderboardModel(
    weblink = weblink,
    game = game,
    category = category,
    level = level,
    platform = platform,
    region = region,
    emulators = emulators,
    videoOnly = videoOnly,
    timing = timing,
    runs = runs.map { it.toModel() },
    links = links.map { it.toModel() },
)

fun LeaderboardResponse.Data.LeaderboardRun.toModel() = LeaderboardModel.LeaderboardRun(
    place = place,
    run = run.toModel(),
)

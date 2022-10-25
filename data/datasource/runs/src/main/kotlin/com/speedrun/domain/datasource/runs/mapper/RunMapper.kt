package com.speedrun.domain.datasource.runs.mapper

import com.speedrun.domain.api.runs.models.*
import com.speedrun.domain.data.datasource.common.mapper.toModel
import com.speedrun.domain.data.datasource.games.mapper.toModel
import com.speedrun.domain.data.datasource.players.mapper.toPlayerModel
import com.speedrun.domain.repo.runs.model.RunModel

fun FlatRunResponse.toModel() = RunModel(
    id = id,
    weblink = weblink,
    game = null,
    level = level,
    category = category,
    videos = videos?.toModel(),
    comment = comment,
    status = status.toModel(),
    players = null,
    date = date,
    submitted = submitted,
    times = times.toModel(),
    system = system.toModel(),
    splits = splits,
    values = values,
    links = null,
)

fun RunResponse.toModel() = RunModel(
    id = id,
    weblink = weblink,
    game = game.data.toModel(),
    level = level,
    category = category,
    videos = videos?.toModel(),
    comment = comment,
    status = status.toModel(),
    players = players.data.map { it.toPlayerModel() },
    date = date,
    submitted = submitted,
    times = times.toModel(),
    system = system.toModel(),
    splits = splits,
    values = values,
    links = links.map { it.toModel() },
)

fun VideosResponse.toModel() = RunModel.Videos(
    links = links.map { it.toModel() }
)

fun StatusResponse.toModel() = RunModel.Status(
    status = status,
    examiner = examiner,
    verifyDate = verifyDate,
)

fun TimesResponse.toModel() = RunModel.Times(
    primary = primary,
    primaryT = primaryT,
    realtime = realtime,
    realtimeT = realtimeT,
    realtimeNoLoads = realtimeNoLoads,
    realtimeNoLoadsT = realtimeNoLoadsT,
    ingame = ingame,
    ingameT = ingameT,
)

fun SystemResponse.toModel() = RunModel.System(
    platform = platform,
    emulated = emulated,
    region = region,
)

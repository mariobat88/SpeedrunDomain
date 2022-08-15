package com.codebox.speedrun.domain.datasource.runs.mapper

import com.codebox.speedrun.data.datasource.common.mapper.toModel
import com.codebox.speedrun.data.datasource.games.mapper.toModel
import com.codebox.speedrun.domain.api.runs.RunResponse
import com.codebox.speedrun.domain.repo.runs.model.RunModel

fun RunResponse.Data.toModel() = RunModel(
    id = id,
    weblink = weblink,
    game = game.data.toModel(),
    level = level,
    category = category,
    videos = videos?.toModel(),
    comment = comment,
    status = status.toModel(),
    players = players.map { it.toModel() },
    date = date,
    submitted = submitted,
    times = times.toModel(),
    system = system.toModel(),
    splits = splits,
    values = values,
    links = links.map { it.toModel() },
)

fun RunResponse.Data.Videos.toModel() = RunModel.Videos(
    links = links.map { it.toModel() }
)

fun RunResponse.Data.Status.toModel() = RunModel.Status(
    status = status,
    examiner = examiner,
    verifyDate = verifyDate,
)

fun RunResponse.Data.Player.toModel() = RunModel.Player(
    rel = rel,
    id = id,
    name = name,
    uri = uri,
)

fun RunResponse.Data.Times.toModel() = RunModel.Times(
    primary = primary,
    primaryT = primaryT,
    realtime = realtime,
    realtimeT = realtimeT,
    realtimeNoLoads = realtimeNoLoads,
    realtimeNoLoadsT = realtimeNoLoadsT,
    ingame = ingame,
    ingameT = ingameT,
)

fun RunResponse.Data.System.toModel() = RunModel.System(
    platform = platform,
    emulated = emulated,
    region = region,
)

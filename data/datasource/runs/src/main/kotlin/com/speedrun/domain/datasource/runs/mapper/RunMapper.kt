package com.speedrun.domain.datasource.runs.mapper

import com.speedrun.domain.api.runs.models.*
import com.speedrun.domain.data.database.entities.GameEntity
import com.speedrun.domain.data.database.entities.PlatformEntity
import com.speedrun.domain.data.database.entities.RunEntity
import com.speedrun.domain.data.database.entities.VideoEntity
import com.speedrun.domain.data.database.result.CategoryResult
import com.speedrun.domain.data.database.result.RunPlayerResult
import com.speedrun.domain.data.datasource.categories.mapper.toCategoryModel
import com.speedrun.domain.data.datasource.common.mapper.toModel
import com.speedrun.domain.data.datasource.games.mapper.toModel
import com.speedrun.domain.data.datasource.platforms.mapper.toPlatformModel
import com.speedrun.domain.data.datasource.players.mapper.toPlayerModel
import com.speedrun.domain.repo.runs.model.RunModel

fun RunEntity.toRunModel(
    gameEntity: GameEntity?,
    categoryResult: CategoryResult?,
    platformEntity: PlatformEntity?,
    runPlayerResults: List<RunPlayerResult>,
    videos:List<VideoEntity>,
) = RunModel(
    id = id,
    weblink = weblink,
    game = gameEntity?.toModel(),
    level = level,
    category = categoryResult?.toCategoryModel(),
    videos = videos.map { it.link },
    comment = comment,
    status = status.toModel(),
    players = runPlayerResults.map { it.players.map { it.toPlayerModel() } }.flatten(),
    date = date,
    submitted = submitted,
    times = times.toModel(),
    system = system.toModel(platformEntity),
    splits = null,
    values = null,
    links = null,
)

private fun RunEntity.Status.toModel() = RunModel.Status(
    status = status,
    examiner = examiner,
    verifyDate = verifyDate,
)

private fun RunEntity.Times.toModel() = RunModel.Times(
    primary = primary,
    primaryT = primaryT,
    realtime = realtime,
    realtimeT = realtimeT,
    realtimeNoLoads = realtimeNoLoads,
    realtimeNoLoadsT = realtimeNoLoadsT,
    ingame = ingame,
    ingameT = ingameT,
)

private fun RunEntity.System.toModel(
    platformEntity: PlatformEntity?,
) = RunModel.System(
    platform = platformEntity?.toPlatformModel(),
    emulated = emulated,
    region = region,
)

fun FlatRunResponse.toRunEntity() = RunEntity(
    id = id,
    weblink = weblink,
    game = game,
    level = level,
    category = category,
    //videos = videos?.toModel(),
    comment = comment,
    status = status.toEntity(),
    date = date,
    submitted = submitted,
    times = times.toEntity(),
    system = system.toEntity(),
)

fun VideosResponse.toEntity(runId: String): List<VideoEntity> {
    return links.map { VideoEntity(runId = runId, link = it.uri) }
}

private fun StatusResponse.toEntity() = RunEntity.Status(
    status = status,
    examiner = examiner,
    verifyDate = verifyDate,
)

private fun TimesResponse.toEntity() = RunEntity.Times(
    primary = primary,
    primaryT = primaryT,
    realtime = realtime,
    realtimeT = realtimeT,
    realtimeNoLoads = realtimeNoLoads,
    realtimeNoLoadsT = realtimeNoLoadsT,
    ingame = ingame,
    ingameT = ingameT,
)

private fun SystemResponse.toEntity() = RunEntity.System(
    platform = platform,
    emulated = emulated,
    region = region,
)

fun RunResponse.toModel() = RunModel(
    id = id,
    weblink = weblink,
    game = game.data.toModel(),
    level = level,
    category = null,
    //category = category,
    videos = videos?.links?.map { it.uri } ?: emptyList(),
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
    platform = null,
    emulated = emulated,
    region = region,
)

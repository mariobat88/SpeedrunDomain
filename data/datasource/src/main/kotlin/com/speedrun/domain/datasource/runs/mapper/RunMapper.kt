package com.speedrun.domain.datasource.runs.mapper

import com.speedrun.domain.api.runs.models.*
import com.speedrun.domain.data.database.entities.*
import com.speedrun.domain.data.database.result.CategoryResult
import com.speedrun.domain.data.database.result.RunPlayerResult
import com.speedrun.domain.datasource.categories.mapper.toCategoryModel
import com.speedrun.domain.datasource.common.mapper.toModel
import com.speedrun.domain.datasource.games.mapper.toModel
import com.speedrun.domain.datasource.platforms.mapper.toPlatformModel
import com.speedrun.domain.datasource.players.mapper.toPlayerModel
import com.speedrun.domain.repo.runs.model.RunModel

fun RunEntity.toRunModel(
    gameEntity: GameEntity?,
    categoryResult: CategoryResult?,
    platformEntity: PlatformEntity?,
    runPlayerResults: List<RunPlayerResult>,
    videos: List<VideoEntity>,
    runValueEntities: List<RunValueEntity>,
) = RunModel(
    id = id,
    weblink = weblink,
    game = gameEntity?.toModel(),
    level = level,
    category = categoryResult?.toCategoryModel(),
    videos = videos.map { it.link },
    comment = comment,
    status = status.toModel(),
    players = runPlayerResults.map { it.players.mapNotNull { it.toPlayerModel() } }.flatten(),
    date = date,
    submitted = submitted,
    times = times.toModel(),
    system = system.toModel(platformEntity),
    splits = null,
    values = runValueEntities.associate { it.variableId to it.valueId },
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
    gameId = game,
    level = level,
    categoryId = category,
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
    game = game?.data?.toModel(),
    level = level,
    category = null,
    videos = videos?.links?.map { it.uri } ?: emptyList(),
    comment = comment,
    status = status.toModel(),
    players = players?.data?.map { it.toPlayerModel() } ?: emptyList(),
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

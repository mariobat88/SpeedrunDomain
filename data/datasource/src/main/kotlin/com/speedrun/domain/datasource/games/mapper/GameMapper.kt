package com.speedrun.domain.datasource.games.mapper

import com.speedrun.data.common.enums.RunTimeEnum
import com.speedrun.domain.api.games.models.GameResponse
import com.speedrun.domain.api.pagination.models.PaginationResponse
import com.speedrun.domain.data.database.entities.GameEntity
import com.speedrun.domain.datasource.pagination.mapper.toModel
import com.speedrun.domain.repo.games.model.GameModel
import com.speedrun.domain.repo.pagination.model.PaginationModel

fun PaginationResponse<GameResponse>.toModel() = PaginationModel(
    data = data.map { it.toModel() },
    pagination = pagination.toModel()
)

fun GameResponse.toEntity() = GameEntity(
    id = id,
    names = names.toEntity(),
    boostReceived = boostReceived,
    boostDistinctDonors = boostDistinctDonors,
    abbreviation = abbreviation,
    weblink = weblink,
    discord = discord,
    released = released,
    releaseDate = releaseDate,
    ruleset = ruleset.toEntity(),
    romhack = romhack,
    created = created,
    assets = assets.toEntity(),
)


fun GameResponse.toModel() = GameModel(
    id = id,
    names = names.toModel(),
    boostReceived = boostReceived,
    boostDistinctDonors = boostDistinctDonors,
    abbreviation = abbreviation,
    weblink = weblink,
    discord = discord,
    released = released,
    releaseDate = releaseDate,
    ruleset = ruleset.toModel(),
    romhack = romhack,
    gametypes = gametypes,
    //platforms = platforms,
    platforms = emptyList(),
    regions = regions,
    genres = genres,
    engines = engines,
    developers = developers,
    publishers = publishers,
    moderators = moderators,
    created = created,
    assets = assets.toModel(),
)

fun GameEntity.toModel() = GameModel(
    id = id,
    names = names.toEntity(),
    boostReceived = boostReceived,
    boostDistinctDonors = boostDistinctDonors,
    abbreviation = abbreviation,
    weblink = weblink,
    discord = discord,
    released = released,
    releaseDate = releaseDate,
    ruleset = ruleset.toEntity(),
    romhack = romhack,
    gametypes = emptyList(),
    platforms = emptyList(),
    regions = emptyList(),
    genres = emptyList(),
    engines = emptyList(),
    developers = emptyList(),
    publishers = emptyList(),
    moderators = emptyMap(),
    created = created,
    assets = assets?.toEntity(),
)

fun GameEntity.Names.toEntity() = GameModel.Names(
    international = international,
    japanese = japanese,
    twitch = twitch,
)

fun GameEntity.Ruleset.toEntity(
    runTimes: List<RunTimeEnum>? = null,
) = GameModel.Ruleset(
    showMilliseconds = showMilliseconds,
    requireVerification = requireVerification,
    requireVideo = requireVideo,
    runTimes = runTimes,
    defaultTime = defaultTime,
    emulatorsAllowed = emulatorsAllowed,
)

fun GameEntity.Assets.toEntity() = GameModel.Assets(
    logo = logo,
    coverTiny = coverTiny,
    coverSmall = coverSmall,
    coverMedium = coverMedium,
    coverLarge = coverLarge,
    icon = icon,
    trophy1st = trophy1st,
    trophy2nd = trophy2nd,
    trophy3rd = trophy3rd,
    trophy4th = trophy4th,
    background = background,
    foreground = foreground,
)

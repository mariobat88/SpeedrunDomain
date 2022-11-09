package com.speedrun.domain.data.datasource.games.mapper

import com.speedrun.data.common.enums.RunTimeEnum
import com.speedrun.domain.data.database.entities.GameEntity
import com.speedrun.domain.data.datasource.common.mapper.toModel
import com.speedrun.domain.data.datasource.common.mapper.toNamesEntity
import com.speedrun.domain.data.datasource.common.mapper.toNamesModel
import com.speedrun.domain.data.pagination.PaginationModel
import com.speedrun.domain.data.pagination.toModel
import com.speedrun.domain.data.repo.games.model.GameModel
import com.speedrun.domain.networking.api.games.GameResponse
import com.speedrun.domain.networking.api.pagination.PaginationResponse

fun PaginationResponse<GameResponse>.toModel() = PaginationModel(
    data = data.map { it.toModel() },
    pagination = pagination.toModel()
)

fun GameResponse.toGameEntity() = GameEntity(
    id = id,
    names = names.toNamesEntity(),
    boostReceived = boostReceived,
    boostDistinctDonors = boostDistinctDonors,
    abbreviation = abbreviation,
    weblink = weblink,
    discord = discord,
    released = released,
    releaseDate = releaseDate,
    ruleset = ruleset.toEntity(),
    romhack = romhack,
//    gametypes = gametypes,
//    platforms = platforms,
//    regions = regions,
//    genres = genres,
//    engines = engines,
//    developers = developers,
//    publishers = publishers,
    //moderators = moderators,
    created = created,
    assets = assets.toEntity(),
//    links = links.map { it.toModel() },
)

private fun GameResponse.Ruleset.toEntity() = GameEntity.Ruleset(
    showMilliseconds = showMilliseconds,
    requireVerification = requireVerification,
    requireVideo = requireVideo,
    defaultTime = defaultTime,
    emulatorsAllowed = emulatorsAllowed,
)

fun GameResponse.Assets.toEntity() = GameEntity.Assets(
    logo = logo.uri,
    coverTiny = coverTiny.uri,
    coverSmall = coverSmall.uri,
    coverMedium = coverMedium.uri,
    coverLarge = coverLarge.uri,
    icon = icon.uri,
    trophy1st = trophy1st.uri,
    trophy2nd = trophy2nd.uri,
    trophy3rd = trophy3rd.uri,
    trophy4th = trophy4th.uri,
    background = background.uri,
    foreground = foreground.uri,
)

fun GameResponse.toModel() = GameModel(
    id = id,
    names = names.toNamesModel(),
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
    links = links.map { it.toModel() },
)

fun GameResponse.Ruleset.toModel() = GameModel.Ruleset(
    showMilliseconds = showMilliseconds,
    requireVerification = requireVerification,
    requireVideo = requireVideo,
    runTimes = runTimes,
    defaultTime = defaultTime,
    emulatorsAllowed = emulatorsAllowed,
)

fun GameResponse.Assets.toModel() = GameModel.Assets(
    logo = logo.uri,
    coverTiny = coverTiny.uri,
    coverSmall = coverSmall.uri,
    coverMedium = coverMedium.uri,
    coverLarge = coverLarge.uri,
    icon = icon.uri,
    trophy1st = trophy1st.uri,
    trophy2nd = trophy2nd.uri,
    trophy3rd = trophy3rd.uri,
    trophy4th = trophy4th.uri,
    background = background.uri,
    foreground = foreground.uri,
)

fun GameEntity.toModel() = GameModel(
    id = id,
    names = names.toNamesModel( ),
    boostReceived = boostReceived,
    boostDistinctDonors = boostDistinctDonors,
    abbreviation = abbreviation,
    weblink = weblink,
    discord = discord,
    released = released,
    releaseDate = releaseDate,
    ruleset = ruleset.toGameEntity(),
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
    assets = assets?.toGameEntity(),
    links = emptyList(),
)

fun GameEntity.Ruleset.toGameEntity(
    runTimes: List<RunTimeEnum>? = null,
) = GameModel.Ruleset(
    showMilliseconds = showMilliseconds,
    requireVerification = requireVerification,
    requireVideo = requireVideo,
    runTimes = runTimes,
    defaultTime = defaultTime,
    emulatorsAllowed = emulatorsAllowed,
)

fun GameEntity.Assets.toGameEntity() = GameModel.Assets(
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

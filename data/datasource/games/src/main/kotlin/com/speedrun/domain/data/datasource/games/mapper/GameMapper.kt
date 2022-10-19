package com.speedrun.domain.data.datasource.games.mapper

import com.speedrun.data.common.enums.RunTimeEnum
import com.speedrun.domain.data.database.entities.GameEntity
import com.speedrun.domain.data.datasource.common.mapper.toModel
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
    names = names.toGameEntity(),
    boostReceived = boostReceived,
    boostDistinctDonors = boostDistinctDonors,
    abbreviation = abbreviation,
    weblink = weblink,
    discord = discord,
    released = released,
    releaseDate = releaseDate,
    ruleset = ruleset.toGameEntity(),
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
    assets = assets.toGameEntity(),
//    links = links.map { it.toModel() },
)

private fun GameResponse.Names.toGameEntity() = GameEntity.Names(
    international = international,
    japanese = japanese,
    twitch = twitch,
)

private fun GameResponse.Ruleset.toGameEntity() = GameEntity.Ruleset(
    showMilliseconds = showMilliseconds,
    requireVerification = requireVerification,
    requireVideo = requireVideo,
    defaultTime = defaultTime,
    emulatorsAllowed = emulatorsAllowed,
)

fun GameResponse.Assets.toGameEntity() = GameEntity.Assets(
    logo = logo.toGameEntity(),
    coverTiny = coverTiny.toGameEntity(),
    coverSmall = coverSmall.toGameEntity(),
    coverMedium = coverMedium.toGameEntity(),
    coverLarge = coverLarge.toGameEntity(),
    icon = icon.toGameEntity(),
    trophy1st = trophy1st.toGameEntity(),
    trophy2nd = trophy2nd.toGameEntity(),
    trophy3rd = trophy3rd.toGameEntity(),
    trophy4th = trophy4th.toGameEntity(),
    background = background.toGameEntity(),
    foreground = foreground.toGameEntity(),
)

fun GameResponse.Assets.Asset.toGameEntity() = GameEntity.Assets.Asset(
    uri = uri,
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
    platforms = platforms,
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

fun GameResponse.Names.toModel() = GameModel.Names(
    international = international,
    japanese = japanese,
    twitch = twitch,
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
    logo = logo.toModel(),
    coverTiny = coverTiny.toModel(),
    coverSmall = coverSmall.toModel(),
    coverMedium = coverMedium.toModel(),
    coverLarge = coverLarge.toModel(),
    icon = icon.toModel(),
    trophy1st = trophy1st.toModel(),
    trophy2nd = trophy2nd.toModel(),
    trophy3rd = trophy3rd.toModel(),
    trophy4th = trophy4th.toModel(),
    background = background.toModel(),
    foreground = foreground.toModel(),
)

fun GameResponse.Assets.Asset.toModel() = GameModel.Assets.Asset(
    uri = uri,
)

fun GameEntity.toModel() = GameModel(
    id = id,
    names = names.toGameEntity(),
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
    assets = assets.toGameEntity(),
    links = emptyList(),
)

fun GameEntity.Names.toGameEntity() = GameModel.Names(
    international = international,
    japanese = japanese,
    twitch = twitch,
)

fun GameEntity.Ruleset.toGameEntity(
    runTimes: List<RunTimeEnum>? = null
) = GameModel.Ruleset(
    showMilliseconds = showMilliseconds,
    requireVerification = requireVerification,
    requireVideo = requireVideo,
    runTimes = runTimes,
    defaultTime = defaultTime,
    emulatorsAllowed = emulatorsAllowed,
)

fun GameEntity.Assets.toGameEntity() = GameModel.Assets(
    logo = logo.toGameEntity(),
    coverTiny = coverTiny.toGameEntity(),
    coverSmall = coverSmall.toGameEntity(),
    coverMedium = coverMedium.toGameEntity(),
    coverLarge = coverLarge.toGameEntity(),
    icon = icon.toGameEntity(),
    trophy1st = trophy1st.toGameEntity(),
    trophy2nd = trophy2nd.toGameEntity(),
    trophy3rd = trophy3rd.toGameEntity(),
    trophy4th = trophy4th?.toGameEntity(),
    background = background?.toGameEntity(),
    foreground = foreground?.toGameEntity(),
)

fun GameEntity.Assets.Asset.toGameEntity() = GameModel.Assets.Asset(
    uri = uri,
)

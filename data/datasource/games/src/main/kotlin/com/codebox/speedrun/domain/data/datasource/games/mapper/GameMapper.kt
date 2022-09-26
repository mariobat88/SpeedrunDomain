package com.codebox.speedrun.domain.data.datasource.games.mapper

import com.codebox.speedrun.domain.data.database.entities.GameEntity
import com.codebox.speedrun.domain.data.datasource.common.mapper.toModel
import com.codebox.speedrun.domain.data.repo.games.model.GameModel
import com.codebox.speedrun.domain.networking.api.games.GameResponse

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
//    gametypes = gametypes,
//    platforms = platforms,
//    regions = regions,
//    genres = genres,
//    engines = engines,
//    developers = developers,
//    publishers = publishers,
    //moderators = moderators,
    created = created,
//    assets = assets.toModel(),
//    links = links.map { it.toModel() },
)

private fun GameResponse.Names.toEntity() = GameEntity.Names(
    international = international,
    japanese = japanese,
    twitch = twitch,
)

private fun GameResponse.Ruleset.toEntity() = GameEntity.Ruleset(
    showMilliseconds = showMilliseconds,
    requireVerification = requireVerification,
    requireVideo = requireVideo,
       //runTimes = runTimes,
    defaultTime = defaultTime,
    emulatorsAllowed = emulatorsAllowed,
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

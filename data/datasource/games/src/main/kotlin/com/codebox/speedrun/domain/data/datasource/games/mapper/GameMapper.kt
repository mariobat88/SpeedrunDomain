package com.codebox.speedrun.domain.data.datasource.games.mapper

import com.codebox.speedrun.domain.data.datasource.common.mapper.toModel
import com.codebox.speedrun.domain.networking.api.games.GameResponse
import com.codebox.speedrun.domain.data.repo.games.model.GameModel

fun GameResponse.Data.toModel() = GameModel(
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

fun GameResponse.Data.Names.toModel() = GameModel.Names(
    international = international,
    japanese = japanese,
    twitch = twitch,
)

fun GameResponse.Data.Ruleset.toModel() = GameModel.Ruleset(
    showMilliseconds = showMilliseconds,
    requireVerification = requireVerification,
    requireVideo = requireVideo,
    runTimes = runTimes,
    defaultTime = defaultTime,
    emulatorsAllowed = emulatorsAllowed,
)

fun GameResponse.Data.Assets.toModel() = GameModel.Assets(
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

fun GameResponse.Data.Assets.Asset.toModel() = GameModel.Assets.Asset(
    uri = uri,
)

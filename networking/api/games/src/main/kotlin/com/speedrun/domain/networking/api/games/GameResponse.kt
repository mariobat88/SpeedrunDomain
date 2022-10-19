package com.speedrun.domain.networking.api.games

import com.speedrun.data.common.enums.RunTimeEnum
import com.speedrun.domain.networking.api.common.LinkResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GameResponse(
    @Json(name = "id") val id: String,
    @Json(name = "names") val names: Names,
    @Json(name = "boostReceived") val boostReceived: Int,
    @Json(name = "boostDistinctDonors") val boostDistinctDonors: Int,
    @Json(name = "abbreviation") val abbreviation: String,
    @Json(name = "weblink") val weblink: String,
    @Json(name = "discord") val discord: String?,
    @Json(name = "released") val released: Int,
    @Json(name = "release-date") val releaseDate: String,
    @Json(name = "ruleset") val ruleset: Ruleset,
    @Json(name = "romhack") val romhack: Boolean,
    @Json(name = "gametypes") val gametypes: List<String>,
    @Json(name = "platforms") val platforms: List<String>,
    @Json(name = "regions") val regions: List<String>,
    @Json(name = "genres") val genres: List<String>,
    @Json(name = "engines") val engines: List<String>,
    @Json(name = "developers") val developers: List<String>,
    @Json(name = "publishers") val publishers: List<String>,
    @Json(name = "moderators") val moderators: Map<String, String>,
    @Json(name = "created") val created: String?,
    @Json(name = "assets") val assets: Assets,
    @Json(name = "links") val links: List<LinkResponse>
) {
    @JsonClass(generateAdapter = true)
    data class Names(
        @Json(name = "international") val international: String,
        @Json(name = "japanese") val japanese: String?,
        @Json(name = "twitch") val twitch: String?
    )

    @JsonClass(generateAdapter = true)
    data class Ruleset(
        @Json(name = "show-milliseconds") val showMilliseconds: Boolean,
        @Json(name = "require-verification") val requireVerification: Boolean,
        @Json(name = "require-video") val requireVideo: Boolean,
        @Json(name = "run-times") val runTimes: List<RunTimeEnum>,
        @Json(name = "default-time") val defaultTime: RunTimeEnum,
        @Json(name = "emulators-allowed") val emulatorsAllowed: Boolean
    )

    @JsonClass(generateAdapter = true)
    data class Assets(
        @Json(name = "logo") val logo: Asset,
        @Json(name = "cover-tiny") val coverTiny: Asset,
        @Json(name = "cover-small") val coverSmall: Asset,
        @Json(name = "cover-medium") val coverMedium: Asset,
        @Json(name = "cover-large") val coverLarge: Asset,
        @Json(name = "icon") val icon: Asset,
        @Json(name = "trophy-1st") val trophy1st: Asset,
        @Json(name = "trophy-2nd") val trophy2nd: Asset,
        @Json(name = "trophy-3rd") val trophy3rd: Asset,
        @Json(name = "trophy-4th") val trophy4th: Asset,
        @Json(name = "background") val background: Asset,
        @Json(name = "foreground") val foreground: Asset
    ) {
        @JsonClass(generateAdapter = true)
        data class Asset(
            @Json(name = "uri") val uri: String?
        )
    }
}
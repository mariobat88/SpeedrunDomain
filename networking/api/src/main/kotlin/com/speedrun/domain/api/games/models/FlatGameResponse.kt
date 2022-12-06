package com.speedrun.domain.api.games.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FlatGameResponse(
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
)

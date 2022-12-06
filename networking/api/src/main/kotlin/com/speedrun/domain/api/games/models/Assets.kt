package com.speedrun.domain.api.games.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

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

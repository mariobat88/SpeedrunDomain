package com.codebox.speedrun.domain.networking.api.players

import com.codebox.speedrun.domain.networking.api.common.LinkResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

enum class PlayerType {
    user, guest
}

sealed class PlayerResponse(@Json(name = "rel") val rel: PlayerType) {
    @JsonClass(generateAdapter = true)
    data class UserResponse(
        @Json(name = "id")
        val id: String,
        @Json(name = "names")
        val names: NamesResponse,
        @Json(name = "weblink")
        val weblink: String,
        @Json(name = "name-style")
        val nameStyle: NameStyle,
        @Json(name = "role")
        val role: String,
        @Json(name = "signup")
        val signup: String?,
        @Json(name = "location")
        val location: Location?,
        @Json(name = "twitch")
        val twitch: LinkResponse?,
        @Json(name = "hitbox")
        val hitbox: LinkResponse?,
        @Json(name = "youtube")
        val youtube: LinkResponse?,
        @Json(name = "twitter")
        val twitter: LinkResponse?,
        @Json(name = "speedrunslive")
        val speedrunslive: LinkResponse?,
        @Json(name = "links")
        val links: List<LinkResponse>
    ) : PlayerResponse(PlayerType.user) {

        @JsonClass(generateAdapter = true)
        data class NameStyle(
            @Json(name = "style")
            val style: String,
            @Json(name = "color")
            val color: Color?,
            @Json(name = "color-from")
            val colorFrom: Color?,
            @Json(name = "color-to")
            val colorTo: Color?
        ) {
            @JsonClass(generateAdapter = true)
            data class Color(
                @Json(name = "light")
                val light: String,
                @Json(name = "dark")
                val dark: String
            )
        }

        @JsonClass(generateAdapter = true)
        data class Location(
            @Json(name = "country")
            val country: Country,
            @Json(name = "region")
            val region: Region?
        ) {
            @JsonClass(generateAdapter = true)
            data class Country(
                @Json(name = "code")
                val code: String,
                @Json(name = "names")
                val names: NamesResponse
            )

            @JsonClass(generateAdapter = true)
            data class Region(
                @Json(name = "code")
                val code: String,
                @Json(name = "names")
                val names: NamesResponse
            )
        }
    }

    @JsonClass(generateAdapter = true)
    data class GuestResponse(
        @Json(name = "name")
        val names: String,
        @Json(name = "links")
        val links: List<LinkResponse>
    ) : PlayerResponse(PlayerType.guest)
}

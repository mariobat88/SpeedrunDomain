package com.speedrun.domain.api.players.models

import com.speedrun.domain.api.common.LinkResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

enum class PlayerType {
    user, guest
}

sealed class PolymorphicPlayerResponse(
    @Json(name = "rel") val playerType: PlayerType
) {
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
        @Json(name = "assets")
        val assets: Assets,
        @Json(name = "links")
        val links: List<LinkResponse>
    ) : PolymorphicPlayerResponse(PlayerType.user) {

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

        @JsonClass(generateAdapter = true)
        data class Assets(
            @Json(name = "icon")
            val icon: Asset?,
            @Json(name = "supporterIcon")
            val supporterIcon: Asset?,
            @Json(name = "image")
            val image: Asset?,
        ) {

            @JsonClass(generateAdapter = true)
            data class Asset(
                @Json(name = "uri")
                val uri: String?,
            )
        }
    }

    @JsonClass(generateAdapter = true)
    data class GuestResponse(
        @Json(name = "name")
        val name: String,
        @Json(name = "links")
        val links: List<LinkResponse>
    ) : PolymorphicPlayerResponse(PlayerType.guest)
}

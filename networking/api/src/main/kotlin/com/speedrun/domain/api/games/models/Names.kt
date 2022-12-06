package com.speedrun.domain.api.games.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Names(
    @Json(name = "international") val international: String,
    @Json(name = "japanese") val japanese: String?,
    @Json(name = "twitch") val twitch: String?
)

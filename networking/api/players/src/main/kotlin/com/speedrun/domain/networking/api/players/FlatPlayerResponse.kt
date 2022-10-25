package com.speedrun.domain.networking.api.players

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FlatPlayerResponse(
    @Json(name = "rel") val rel: String,
    @Json(name = "id") val id: String,
    @Json(name = "uri") val uri: String,
)

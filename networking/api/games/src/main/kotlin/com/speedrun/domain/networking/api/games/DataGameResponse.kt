package com.speedrun.domain.networking.api.games

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataGameResponse(
    @Json(name = "data") val data: GameResponse
)

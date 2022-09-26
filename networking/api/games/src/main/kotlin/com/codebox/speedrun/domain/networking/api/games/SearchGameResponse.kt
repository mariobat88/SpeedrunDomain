package com.codebox.speedrun.domain.networking.api.games

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchGameResponse(
    @Json(name = "data") val data: List<GameResponse>
)

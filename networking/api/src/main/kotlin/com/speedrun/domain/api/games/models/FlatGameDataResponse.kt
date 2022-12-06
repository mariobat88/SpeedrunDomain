package com.speedrun.domain.api.games.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FlatGameDataResponse(
    @Json(name = "data") val data: FlatGameResponse,
)

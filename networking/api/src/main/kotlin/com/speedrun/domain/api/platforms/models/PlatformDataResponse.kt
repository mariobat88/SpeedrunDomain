package com.speedrun.domain.api.platforms.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlatformDataResponse(
    @Json(name = "data")
    val data: List<PlatformResponse>
)
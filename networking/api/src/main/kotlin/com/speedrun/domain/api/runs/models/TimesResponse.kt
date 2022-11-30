package com.speedrun.domain.api.runs.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TimesResponse(
    @Json(name = "primary") val primary: String,
    @Json(name = "primary_t") val primaryT: Float,
    @Json(name = "realtime") val realtime: String?,
    @Json(name = "realtime_t") val realtimeT: Float,
    @Json(name = "realtime_noloads") val realtimeNoLoads: String?,
    @Json(name = "realtime_noloads_t") val realtimeNoLoadsT: Float,
    @Json(name = "ingame") val ingame: String?,
    @Json(name = "ingame_t") val ingameT: Float
)
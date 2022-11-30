package com.speedrun.domain.api.runs.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SystemResponse(
    @Json(name = "platform") val platform: String?,
    @Json(name = "emulated") val emulated: Boolean,
    @Json(name = "region") val region: String?
)

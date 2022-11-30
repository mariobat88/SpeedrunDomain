package com.speedrun.domain.api.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LinkResponse(
    @Json(name = "rel") val rel: String?,
    @Json(name = "uri") val uri: String
)

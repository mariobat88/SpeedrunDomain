package com.speedrun.domain.networking.api.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NamesResponse(
    @Json(name = "international") val international: String,
    @Json(name = "japanese") val japanese: String?
)

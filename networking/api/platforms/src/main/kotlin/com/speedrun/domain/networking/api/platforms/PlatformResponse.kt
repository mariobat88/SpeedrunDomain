package com.speedrun.domain.networking.api.platforms

import com.speedrun.domain.networking.api.common.LinkResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlatformResponse(
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "released")
    val released: Int,
    @Json(name = "links")
    val links: List<LinkResponse>
)

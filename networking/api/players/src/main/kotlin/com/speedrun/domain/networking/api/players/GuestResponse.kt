package com.speedrun.domain.networking.api.players

import com.speedrun.domain.networking.api.common.LinkResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GuestResponse(
    @Json(name = "name")
    val name: String,
    @Json(name = "links")
    val links: List<LinkResponse>
)

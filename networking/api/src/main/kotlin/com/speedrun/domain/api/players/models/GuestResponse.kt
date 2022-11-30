package com.speedrun.domain.api.players.models

import com.speedrun.domain.api.common.LinkResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GuestResponse(
    @Json(name = "name")
    val name: String,
    @Json(name = "links")
    val links: List<LinkResponse>
)

package com.speedrun.domain.api.runs.models

import com.speedrun.domain.api.common.LinkResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideosResponse(
    @Json(name = "links") val links: List<LinkResponse>
)

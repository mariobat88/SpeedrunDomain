package com.speedrun.domain.networking.api.leaderboards

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VariablesDataListResponse(
    @Json(name = "data")
    val data: List<VariablesResponse>
)

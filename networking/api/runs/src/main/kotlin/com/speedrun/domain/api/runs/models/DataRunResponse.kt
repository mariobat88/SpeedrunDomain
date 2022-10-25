package com.speedrun.domain.api.runs.models

import com.speedrun.domain.networking.api.pagination.PaginationResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataRunResponse(
    @Json(name = "data") val data: List<RunResponse>,
    @Json(name = "pagination") val pagination: PaginationResponse.Pagination
)

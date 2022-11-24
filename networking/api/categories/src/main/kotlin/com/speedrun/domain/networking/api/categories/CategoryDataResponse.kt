package com.speedrun.domain.networking.api.categories

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoryDataResponse(
    @Json(name = "data")
    val data: CategoryResponse
)

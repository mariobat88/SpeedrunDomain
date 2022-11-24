package com.speedrun.domain.networking.api.categories

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoryDataListResponse(
    @Json(name = "data")
    val data: List<CategoryResponse>
)
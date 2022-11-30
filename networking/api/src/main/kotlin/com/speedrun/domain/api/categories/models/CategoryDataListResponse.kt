package com.speedrun.domain.api.categories.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoryDataListResponse(
    @Json(name = "data")
    val data: List<CategoryResponse>
)
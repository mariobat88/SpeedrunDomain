package com.speedrun.domain.networking.api.categories

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoriesResponse(
    @Json(name = "data")
    val data: List<CategoryData>
)
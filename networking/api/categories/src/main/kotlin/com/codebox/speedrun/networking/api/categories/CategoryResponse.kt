package com.codebox.speedrun.networking.api.categories

import com.codebox.speedrun.domain.networking.api.common.LinkResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoryResponse(
    @Json(name = "data")
    val data: Data
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "id")
        val id: String,
        @Json(name = "links")
        val links: List<LinkResponse>,
        @Json(name = "miscellaneous")
        val miscellaneous: Boolean,
        @Json(name = "name")
        val name: String,
        @Json(name = "players")
        val players: Players,
        @Json(name = "rules")
        val rules: String?,
        @Json(name = "type")
        val type: String,
        @Json(name = "weblink")
        val weblink: String
    ) {
        @JsonClass(generateAdapter = true)
        data class Players(
            @Json(name = "type")
            val type: String,
            @Json(name = "value")
            val value: Int
        )
    }
}

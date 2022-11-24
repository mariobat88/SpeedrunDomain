package com.speedrun.domain.networking.api.categories

import com.speedrun.data.common.enums.RunTypeEnum
import com.speedrun.domain.networking.api.common.LinkResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoryData(
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "weblink")
    val weblink: String,
    @Json(name = "type")
    val type: RunTypeEnum,
    @Json(name = "rules")
    val rules: String?,
    @Json(name = "players")
    val players: Players,
    @Json(name = "miscellaneous")
    val miscellaneous: Boolean,
    @Json(name = "links")
    val links: List<LinkResponse>
) {
    @JsonClass(generateAdapter = true)
    data class Players(
        @Json(name = "type")
        val type: String,
        @Json(name = "value")
        val value: Int
    )
}

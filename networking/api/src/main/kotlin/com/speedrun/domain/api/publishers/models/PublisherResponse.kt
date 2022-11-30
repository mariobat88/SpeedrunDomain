package com.speedrun.domain.api.publishers.models

import com.speedrun.domain.api.common.LinkResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PublisherResponse(
    @Json(name = "data")
    val data: Data
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "id")
        val id: String,
        @Json(name = "name")
        val name: String,
        @Json(name = "links")
        val links: List<LinkResponse>
    )
}

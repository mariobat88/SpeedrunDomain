package com.speedrun.domain.networking.api.pagination

import com.speedrun.domain.networking.api.common.LinkResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PaginationResponse<T>(
    @Json(name = "data") val data: List<T>,
    @Json(name = "pagination") val pagination: Pagination,
) {
    @JsonClass(generateAdapter = true)
    data class Pagination(
        @Json(name = "offset") val offset: Int,
        @Json(name = "max") val max: Int,
        @Json(name = "size") val size: Int,
        @Json(name = "links") val links: List<LinkResponse>
    )
}

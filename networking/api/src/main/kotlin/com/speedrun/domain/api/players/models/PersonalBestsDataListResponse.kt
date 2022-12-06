package com.speedrun.domain.api.players.models

import com.speedrun.domain.api.categories.models.CategoryDataResponse
import com.speedrun.domain.api.categories.models.CategoryResponse
import com.speedrun.domain.api.games.models.FlatGameDataResponse
import com.speedrun.domain.api.runs.models.FlatRunResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PersonalBestsDataListResponse(
    @Json(name = "data")
    val data: List<PersonalBestData>
) {
    @JsonClass(generateAdapter = true)
    data class PersonalBestData(
        @Json(name = "place")
        val place: Int,
        @Json(name = "run")
        val run: FlatRunResponse,
        @Json(name = "game")
        val game: FlatGameDataResponse,
        @Json(name = "category")
        val category: CategoryDataResponse,
    )
}

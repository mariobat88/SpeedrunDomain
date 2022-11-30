package com.speedrun.domain.api.players.models

import com.speedrun.domain.api.games.models.GameDataResponse
import com.speedrun.domain.api.runs.models.FlatRunResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PersonalBestsDataListResponse(
    @Json(name = "data")
    val data: List<Data>
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "place")
        val place: Int,
        @Json(name = "run")
        val run: FlatRunResponse,
        @Json(name = "game")
        val game: GameDataResponse
    )
}

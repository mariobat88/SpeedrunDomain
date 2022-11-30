package com.speedrun.domain.api.leaderboards.models

import com.speedrun.data.common.enums.RunTimeEnum
import com.speedrun.domain.api.categories.models.CategoryDataResponse
import com.speedrun.domain.api.common.LinkResponse
import com.speedrun.domain.api.players.models.PolymorphicPlayerResponse
import com.speedrun.domain.api.runs.models.FlatRunResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LeaderboardResponse(
    @Json(name = "data")
    val data: Data
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "weblink")
        val weblink: String,
        @Json(name = "game")
        val game: String,
        @Json(name = "category")
        val category: CategoryDataResponse,
        @Json(name = "level")
        val level: String?,
        @Json(name = "platform")
        val platform: String?,
        @Json(name = "region")
        val region: String?,
        @Json(name = "emulators")
        val emulators: Boolean?,
        @Json(name = "video-only")
        val videoOnly: Boolean,
        @Json(name = "timing")
        val timing: RunTimeEnum,
        @Json(name = "runs")
        val runs: List<LeaderboardRun>,
        @Json(name = "links")
        val links: List<LinkResponse>,
        @Json(name = "players")
        val players: Players,
        @Json(name = "variables")
        val variables: VariablesDataListResponse
    ) {
        @JsonClass(generateAdapter = true)
        data class LeaderboardRun(
            @Json(name = "place")
            val place: Int,
            @Json(name = "run")
            val run: FlatRunResponse,
        )

        @JsonClass(generateAdapter = true)
        data class Players(
            @Json(name = "data")
            val data: List<PolymorphicPlayerResponse>,
        )
    }
}

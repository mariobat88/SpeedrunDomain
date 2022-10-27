package com.speedrun.domain.networking.api.leaderboards

import com.speedrun.data.common.enums.RunTimeEnum
import com.speedrun.domain.api.runs.models.FlatRunResponse
import com.speedrun.domain.networking.api.common.LinkResponse
import com.speedrun.domain.networking.api.players.PolymorphicPlayerResponse
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
        val category: String,
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

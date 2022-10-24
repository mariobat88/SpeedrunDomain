package com.speedrun.domain.networking.api.leaderboards


import com.speedrun.domain.networking.api.common.LinkResponse
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
        val level: Any?,
        @Json(name = "platform")
        val platform: Any?,
        @Json(name = "region")
        val region: Any?,
        @Json(name = "emulators")
        val emulators: Boolean?,
        @Json(name = "video-only")
        val videoOnly: Boolean,
        @Json(name = "timing")
        val timing: String,
        @Json(name = "runs")
        val runs: List<Run>,
        @Json(name = "links")
        val links: List<LinkResponse>
    ) {
        @JsonClass(generateAdapter = true)
        data class Run(
            @Json(name = "place")
            val place: Int,
            @Json(name = "run")
            val run: String
        )
    }
}

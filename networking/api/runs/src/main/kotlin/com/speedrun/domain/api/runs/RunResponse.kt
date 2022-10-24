package com.speedrun.domain.api.runs

import com.speedrun.domain.networking.api.common.LinkResponse
import com.speedrun.domain.networking.api.games.DataGameResponse
import com.speedrun.domain.networking.api.players.PolymorphicPlayerResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RunResponse(
    @Json(name = "data") val data: List<Data>,
    @Json(name = "pagination") val pagination: Pagination
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "id") val id: String,
        @Json(name = "weblink") val weblink: String,
        @Json(name = "game") val game: DataGameResponse,
        @Json(name = "level") val level: String?,
        @Json(name = "category") val category: String,
        @Json(name = "videos") val videos: Videos?,
        @Json(name = "comment") val comment: String?,
        @Json(name = "status") val status: Status,
        @Json(name = "players") val players: Players,
        @Json(name = "date") val date: String?,
        @Json(name = "submitted") val submitted: String?,
        @Json(name = "times") val times: Times,
        @Json(name = "system") val system: System,
        @Json(name = "splits") val splits: Any?,
        @Json(name = "values") val values: Map<String, String>?,
        @Json(name = "links") val links: List<LinkResponse>
    ) {

        @JsonClass(generateAdapter = true)
        data class Videos(
            @Json(name = "links") val links: List<LinkResponse>
        )

        @JsonClass(generateAdapter = true)
        data class Status(
            @Json(name = "status") val status: String,
            @Json(name = "examiner") val examiner: String?,
            @Json(name = "verify-date") val verifyDate: String?
        )

        @JsonClass(generateAdapter = true)
        data class Players(
            @Json(name = "data") val data: List<PolymorphicPlayerResponse>,
        )

        @JsonClass(generateAdapter = true)
        data class Times(
            @Json(name = "primary") val primary: String,
            @Json(name = "primary_t") val primaryT: Float,
            @Json(name = "realtime") val realtime: String?,
            @Json(name = "realtime_t") val realtimeT: Float,
            @Json(name = "realtime_noloads") val realtimeNoLoads: String?,
            @Json(name = "realtime_noloads_t") val realtimeNoLoadsT: Float,
            @Json(name = "ingame") val ingame: String?,
            @Json(name = "ingame_t") val ingameT: Float
        )

        @JsonClass(generateAdapter = true)
        data class System(
            @Json(name = "platform") val platform: String?,
            @Json(name = "emulated") val emulated: Boolean,
            @Json(name = "region") val region: String?
        )
    }

    @JsonClass(generateAdapter = true)
    data class Pagination(
        @Json(name = "offset") val offset: Int,
        @Json(name = "max") val max: Int,
        @Json(name = "size") val size: Int,
        @Json(name = "links") val links: List<LinkResponse>
    )
}

package com.speedrun.domain.api.runs.models

import com.speedrun.domain.networking.api.players.FlatPlayerResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FlatRunResponse(
    @Json(name = "id") val id: String,
    @Json(name = "weblink") val weblink: String,
    @Json(name = "game") val game: String,
    @Json(name = "level") val level: String?,
    @Json(name = "category") val category: String,
    @Json(name = "videos") val videos: VideosResponse?,
    @Json(name = "comment") val comment: String?,
    @Json(name = "status") val status: StatusResponse,
    @Json(name = "players") val players: List<FlatPlayerResponse>,
    @Json(name = "date") val date: String?,
    @Json(name = "submitted") val submitted: String?,
    @Json(name = "times") val times: TimesResponse,
    @Json(name = "system") val system: SystemResponse,
    @Json(name = "splits") val splits: Any?,
    @Json(name = "values") val values: Map<String, String>?,
)

package com.codebox.speedrun.domain.datasource.runs.model

import com.codebox.speedrun.domain.api.runs.RunResponse

fun RunResponse.toModel()  = RunModel(
    data = data.toModel()
)

fun RunResponse.Data.toModel() = RunModel.Data(
    id = id,
    weblink = weblink,
)

val id: String,
val weblink: String,
val game: RunModel.Data.Game,
val level: String?,
val category: String,
val videos: RunModel.Data.Videos,
val comment: String?,
val status: RunModel.Data.Status,
val players: List<RunModel.Data.Player>,
val date: String,
val submitted: String,
val times: RunModel.Data.Times,
val system: RunModel.Data.System,
val splits: Any?,
val values: Map<String, String>?,
val links: List<RunModel.Data.Link>
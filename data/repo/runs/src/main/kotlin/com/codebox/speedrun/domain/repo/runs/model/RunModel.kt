package com.codebox.speedrun.domain.repo.runs.model

import com.codebox.speedrun.data.repo.common.model.LinkModel
import com.codebox.speedrundomain.data.repo.games.model.GameModel

data class RunModel(
    val id: String,
    val weblink: String,
    val game: GameModel,
    val level: String?,
    val category: String,
    val videos: Videos?,
    val comment: String?,
    val status: Status,
    val players: List<Player>,
    val date: String,
    val submitted: String,
    val times: Times,
    val system: System,
    val splits: Any?,
    val values: Map<String, String>?,
    val links: List<LinkModel>
) {

    data class Videos(
        val links: List<LinkModel>
    )

    data class Status(
        val status: String,
        val examiner: String,
        val verifyDate: String
    )

    data class Player(
        val rel: String,
        val id: String?,
        val name: String?,
        val uri: String
    )

    data class Times(
        val primary: String,
        val primaryT: Double,
        val realtime: String?,
        val realtimeT: Double,
        val realtimeNoLoads: Any?,
        val realtimeNoLoadsT: Int,
        val ingame: String?,
        val ingameT: Double
    )

    data class System(
        val platform: String,
        val emulated: Boolean,
        val region: String?
    )
}

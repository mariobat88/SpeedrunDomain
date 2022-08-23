package com.codebox.speedrun.domain.repo.runs.model

import com.codebox.speedrun.domain.data.repo.categories.model.CategoryModel
import com.codebox.speedrun.domain.data.repo.common.model.LinkModel
import com.codebox.speedrun.domain.data.repo.games.model.GameModel
import com.codebox.speedrun.domain.data.repo.players.model.PlayerModel

data class RunModel(
    val id: String,
    val weblink: String,
    val game: GameModel,
    val level: String?,
    val category: CategoryModel,
    val videos: Videos?,
    val comment: String?,
    val status: Status,
    val players: List<PlayerModel>,
    val date: String?,
    val submitted: String?,
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
        val examiner: String?,
        val verifyDate: String?
    )

    data class Times(
        val primary: String,
        val primaryT: Float,
        val realtime: String?,
        val realtimeT: Float,
        val realtimeNoLoads: String?,
        val realtimeNoLoadsT: Float,
        val ingame: String?,
        val ingameT: Float
    )

    data class System(
        val platform: String?,
        val emulated: Boolean,
        val region: String?
    )
}

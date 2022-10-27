package com.speedrun.domain.data.repo.leaderboards.model

import com.speedrun.data.common.enums.RunTimeEnum
import com.speedrun.domain.data.repo.common.model.LinkModel
import com.speedrun.domain.data.repo.games.model.GameModel
import com.speedrun.domain.repo.runs.model.RunModel

data class LeaderboardModel(
    val id: String,
    val weblink: String,
    val game: GameModel?,
    val category: String,
    val level: Any?,
    val platform: Any?,
    val region: Any?,
    val emulators: Boolean?,
    val videoOnly: Boolean,
    val timing: RunTimeEnum,
    val runs: List<LeaderboardRun>,
    val links: List<LinkModel>
) {
    data class LeaderboardRun(
        val place: Int,
        val run: RunModel,
    )
}

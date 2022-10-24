package com.speedrun.domain.data.repo.leaderboards.model

import com.speedrun.domain.data.repo.common.model.LinkModel

data class LeaderboardModel(
    val weblink: String,
    val game: String,
    val category: String,
    val level: Any?,
    val platform: Any?,
    val region: Any?,
    val emulators: Boolean?,
    val videoOnly: Boolean,
    val timing: String,
    val runs: List<Run>,
    val links: List<LinkModel>
) {
    data class Run(
        val place: Int,
        val run: String
    )
}

package com.speedrun.domain.repo.leaderboards.model

import com.speedrun.data.common.enums.RunTimeEnum
import com.speedrun.domain.repo.categories.model.CategoryModel
import com.speedrun.domain.repo.common.model.LinkModel
import com.speedrun.domain.repo.games.model.GameModel

data class LeaderboardModel(
    val id: String,
    val weblink: String,
    val game: GameModel?,
    val category: CategoryModel?,
    val level: Any?,
    val platform: String?,
    val region: Any?,
    val emulators: Boolean?,
    val videoOnly: Boolean,
    val timing: RunTimeEnum,
    val runs: List<LeaderboardPlaceModel>,
    val links: List<LinkModel>
)

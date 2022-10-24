package com.speedrun.domain.feature.leaderboards

import com.speedrun.domain.core.framework.async.Async
import com.speedrun.domain.core.framework.async.Uninitialized
import com.speedrun.domain.data.repo.categories.model.CategoryModel
import com.speedrun.domain.data.repo.leaderboards.model.LeaderboardModel

sealed class Intent {
    data class CategorySelected(val index: Int) : Intent()
}

data class ViewState(
    val categoriesAsync: Async<List<CategoryModel>> = Uninitialized,
    val leaderboardsMap: MutableMap<Int, Async<LeaderboardModel>> = mutableMapOf()
)

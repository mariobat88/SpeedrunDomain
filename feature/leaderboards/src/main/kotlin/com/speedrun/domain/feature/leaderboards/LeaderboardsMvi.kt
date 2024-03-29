package com.speedrun.domain.feature.leaderboards

import androidx.compose.runtime.mutableStateMapOf
import com.speedrun.domain.core.framework.async.Async
import com.speedrun.domain.core.framework.async.Uninitialized
import com.speedrun.domain.repo.categories.model.CategoryModel
import com.speedrun.domain.repo.leaderboards.model.LeaderboardModel

sealed class Intent {
    data class CategorySelected(val index: Int) : Intent()
    data class RunClicked(val runId: String?) : Intent()
}

data class ViewState(
    val categoriesAsync: Async<List<CategoryModel>> = Uninitialized,
    val leaderboardsMap: MutableMap<Int, Async<LeaderboardModel>> = mutableStateMapOf()
)

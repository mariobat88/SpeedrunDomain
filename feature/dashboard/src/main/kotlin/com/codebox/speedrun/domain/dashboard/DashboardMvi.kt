package com.codebox.speedrun.domain.dashboard

import androidx.navigation.NavOptions
import com.codebox.speedrun.domain.data.repo.games.model.GameModel
import com.codebox.speedrun.domain.repo.runs.model.RunModel

sealed class Intent {
    data class NavigateToTab(val route: String, val navOptions: NavOptions) : Intent()
}

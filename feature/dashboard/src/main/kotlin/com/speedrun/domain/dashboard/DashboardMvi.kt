package com.speedrun.domain.dashboard

import androidx.navigation.NavOptions

sealed class Intent {
    data class NavigateToTab(val route: String, val navOptions: NavOptions) : Intent()
}

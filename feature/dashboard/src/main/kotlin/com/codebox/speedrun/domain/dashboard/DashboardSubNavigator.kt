package com.codebox.speedrun.domain.dashboard

import androidx.navigation.NavOptions

interface DashboardSubNavigator {
    fun navigateToTab(route: String, navOptions: NavOptions)
}

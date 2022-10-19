package com.speedrun.domain.dashboard.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.speedrun.domain.core.navigation.Destination
import com.speedrun.domain.dashboard.feature.home.HomeScreen

object HomeNavigation : Destination {
    override val route = "dashboard/home_route"
    override val destination: String? = null
}

fun NavGraphBuilder.homeNavigation() {
    composable(route = HomeNavigation.route) {
        HomeScreen()
    }
}
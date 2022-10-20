package com.speedrun.domain.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.speedrun.domain.core.framework.Navigation
import com.speedrun.domain.core.framework.navigation.StateNavigator
import com.speedrun.domain.dashboard.navigation.DashboardNavigation
import com.speedrun.domain.dashboard.navigation.dashboardNavigation
import com.speedrun.domain.feature.game.navigation.gameNavigation

@Composable
fun <T> AppNavigation(
    navController: NavHostController,
    mainNavigator: T,
) where T : AppNavigator, T : StateNavigator {
    NavHost(
        navController = navController,
        startDestination = DashboardNavigation.route
    ) {
        dashboardNavigation(mainNavigator)
        gameNavigation()
    }
    Navigation(navController, mainNavigator)
}
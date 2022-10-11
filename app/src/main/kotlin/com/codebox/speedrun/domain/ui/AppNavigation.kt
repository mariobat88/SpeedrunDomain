package com.codebox.speedrun.domain.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.codebox.speedrun.domain.core.framework.Navigation
import com.codebox.speedrun.domain.core.framework.navigation.StateNavigator
import com.codebox.speedrun.domain.dashboard.DashboardNavigator
import com.codebox.speedrun.domain.dashboard.DashboardScreen
import com.codebox.speedrun.domain.feature.game.GameScreen

@Composable
fun <T> AppNavigation(
    navController: NavHostController,
    mainNavigator: T,
) where T : AppNavigator, T : StateNavigator {
    NavHost(
        navController = navController,
        startDestination = "dashboard"
    ) {
        dashboardNavigation(mainNavigator)
        gameNavigation()
    }
    Navigation(navController, mainNavigator)
}

fun NavGraphBuilder.dashboardNavigation(
    dashboardNavigator: DashboardNavigator
) {
    navigation(
        route = "dashboard",
        startDestination = "main"
    ) {
        composable(route = "main") {
            DashboardScreen(dashboardNavigator)
        }
    }
}

fun NavGraphBuilder.gameNavigation() {
    navigation(
        route = "game",
        startDestination = "main"
    ) {
        composable(route = "main") {
            GameScreen()
        }
    }
}
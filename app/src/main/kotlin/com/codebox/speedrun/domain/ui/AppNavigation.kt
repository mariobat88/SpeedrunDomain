package com.codebox.speedrun.domain.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.codebox.speedrun.domain.dashboard.DashboardScreen
import com.codebox.speedrun.domain.feature.game.GameScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "dashboard"
    ) {
        DashboardNavigation()
        GameNavigation()
    }
}

fun NavGraphBuilder.DashboardNavigation() {
    navigation(
        route = "dashboard",
        startDestination = "main"
    ) {
        composable(route = "main") {
            DashboardScreen()
        }
    }
}

fun NavGraphBuilder.GameNavigation() {
    navigation(
        route = "game",
        startDestination = "main"
    ) {
        composable(route = "main") {
            GameScreen()
        }
    }
}
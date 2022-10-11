package com.codebox.speedrun.domain.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codebox.speedrun.domain.core.framework.Navigation
import com.codebox.speedrun.domain.dashboard.home.HomeScreen
import com.codebox.speedrun.domain.dashboard.search.SearchScreen

@Composable
fun DashboardNavigation(
    navController: NavHostController,
    dashboardNavigator: DashboardNavigator,
    dashboardViewModel: DashboardViewModel,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable(route = "home") {
            HomeScreen()
        }
        composable(route = "search") {
            SearchScreen(dashboardNavigator)
        }
    }
    Navigation(navController, dashboardViewModel)
}

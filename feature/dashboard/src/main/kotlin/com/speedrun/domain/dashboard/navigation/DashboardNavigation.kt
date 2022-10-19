package com.speedrun.domain.dashboard.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.speedrun.domain.core.framework.Navigation
import com.speedrun.domain.core.navigation.Destination
import com.speedrun.domain.dashboard.DashboardScreen
import com.speedrun.domain.dashboard.DashboardViewModel
import com.speedrun.domain.dashboard.feature.home.navigation.HomeNavigation
import com.speedrun.domain.dashboard.feature.home.navigation.homeNavigation
import com.speedrun.domain.dashboard.feature.search.navigation.searchNavigation

object DashboardNavigation : Destination {
    override val route = "dashboard_route"
    override val destination = "dashboard_destination"
}

fun NavGraphBuilder.dashboardNavigation(
    dashboardNavigator: DashboardNavigator
) {
    navigation(
        route = DashboardNavigation.route,
        startDestination = DashboardNavigation.destination,
    ) {
        composable(route = DashboardNavigation.destination) {
            DashboardScreen(dashboardNavigator)
        }
    }
}

@Composable
fun DashboardSubNavigation(
    navController: NavHostController,
    dashboardNavigator: DashboardNavigator,
    dashboardViewModel: DashboardViewModel,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeNavigation.route,
        modifier = modifier
    ) {
        homeNavigation()
        searchNavigation(dashboardNavigator)
    }
    Navigation(navController, dashboardViewModel)
}

package com.speedrun.domain.feature.leaderboards.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.speedrun.domain.core.navigation.Destination
import com.speedrun.domain.feature.leaderboards.LeaderboardsScreen

object LeaderboardsNavigation : Destination {
    const val gameIdArg = "gameId"

    operator fun invoke(gameId:String) = "leaderboards_route/$gameId"

    override val route = "leaderboards_route/{$gameIdArg}"

    override val destination = "leaderboards_destination"
}

fun NavGraphBuilder.leaderboardsNavigation() {
    navigation(
        route = LeaderboardsNavigation.route,
        startDestination = LeaderboardsNavigation.destination,
        arguments = listOf(navArgument(LeaderboardsNavigation.gameIdArg) { type = NavType.StringType })
    ) {
        composable(route = LeaderboardsNavigation.destination) {
            LeaderboardsScreen()
        }
    }
}


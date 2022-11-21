package com.speedrun.domain.feature.run.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.speedrun.domain.core.navigation.Destination
import com.speedrun.domain.feature.run.RunScreen

object RunNavigation : Destination {
    const val runIdArg = "runId"

    operator fun invoke(gameId:String) = "run_route/$gameId"

    override val route = "run_route/{$runIdArg}"

    override val destination = "run_destination"
}

fun NavGraphBuilder.runNavigation(
    runNavigator: RunNavigator,
) {
    navigation(
        route = RunNavigation.route,
        startDestination = RunNavigation.destination,
        arguments = listOf(navArgument(RunNavigation.runIdArg) { type = NavType.StringType })
    ) {
        composable(route = RunNavigation.destination) {
            RunScreen(runNavigator)
        }
    }
}

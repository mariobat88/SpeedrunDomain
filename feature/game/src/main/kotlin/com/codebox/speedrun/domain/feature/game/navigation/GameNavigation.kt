package com.codebox.speedrun.domain.feature.game.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.codebox.speedrun.domain.core.navigation.Destination
import com.codebox.speedrun.domain.feature.game.GameScreen

object GameNavigation : Destination {
    const val gameIdArg = "gameId"

    operator fun invoke(gameId:String) = "game_route/$gameId"

    override val route = "game_route/{$gameIdArg}"

    override val destination = "game_destination"
}

fun NavGraphBuilder.gameNavigation() {
    navigation(
        route = GameNavigation.route,
        startDestination = GameNavigation.destination,
        arguments = listOf(navArgument(GameNavigation.gameIdArg) { type = NavType.StringType })
    ) {
        composable(route = GameNavigation.destination) {  backStackEntry ->
            val gameId = backStackEntry.arguments?.getString(GameNavigation.gameIdArg)!!
            GameScreen(gameId)
        }
    }
}

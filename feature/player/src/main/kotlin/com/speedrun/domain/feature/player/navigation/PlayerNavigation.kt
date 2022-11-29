package com.speedrun.domain.feature.player.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.speedrun.domain.core.navigation.Destination
import com.speedrun.domain.feature.player.PlayerScreen

object PlayerNavigation : Destination {
    const val playerIdArg = "playerId"

    operator fun invoke(gameId: String) = "player_route/$gameId"

    override val route = "player_route/{$playerIdArg}"

    override val destination = "player_destination"
}

fun NavGraphBuilder.playerNavigation(
    playerNavigator: PlayerNavigator
) {
    navigation(
        route = PlayerNavigation.route,
        startDestination = PlayerNavigation.destination,
        arguments = listOf(navArgument(PlayerNavigation.playerIdArg) { type = NavType.StringType })
    ) {
        composable(route = PlayerNavigation.destination) {
            PlayerScreen(playerNavigator)
        }
    }
}


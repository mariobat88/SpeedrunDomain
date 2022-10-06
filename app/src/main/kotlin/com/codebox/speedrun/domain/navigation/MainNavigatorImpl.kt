package com.codebox.speedrun.domain.navigation

import com.codebox.speedrun.domain.core.navigation.MainNavigator
import com.codebox.speedrun.domain.feature.game.destinations.GameScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

class MainNavigatorImpl(
    private val destinationsNavigator: DestinationsNavigator,
) : MainNavigator {

    override fun navigateToGameScreen() {
        destinationsNavigator.navigate(GameScreenDestination)
    }
}

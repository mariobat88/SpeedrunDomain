package com.speedrun.domain.feature.game.navigation

interface GameNavigator {
    fun backClicked()
    fun navigateToLeaderboardsScreen(gameId: String)
}

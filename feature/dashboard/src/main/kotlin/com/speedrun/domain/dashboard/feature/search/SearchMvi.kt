package com.speedrun.domain.dashboard.feature.search

sealed class Intent {
    data class TabSelected(val index: Int) : Intent()
    data class Search(val searchTerm: String) : Intent()
    data class NavigateToGameScreen(val gameId: String) : Intent()
}

data class ViewState(
    val selectedTab: TAB = TAB.GAMES
) {
    enum class TAB {
        GAMES, PLAYERS
    }
}

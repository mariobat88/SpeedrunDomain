package com.codebox.speedrun.domain.dashboard.feature.search

sealed class Intent {
    data class TabSelected(val index: Int) : Intent()
    data class Search(val searchTerm: String) : Intent()
    object NavigateToGameScreen : Intent()
}

data class ViewState(
    val selectedTab: TAB = TAB.GAMES
) {
    enum class TAB {
        GAMES, PLAYERS
    }
}

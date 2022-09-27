package com.codebox.speedrun.domain.dashboard.search

sealed class Intent {
    data class TabSelected(val index: Int) : Intent()
    data class Search(val searchTerm: String) : Intent()
}

data class ViewState(
    val selectedTabIndex: Int = 0
)

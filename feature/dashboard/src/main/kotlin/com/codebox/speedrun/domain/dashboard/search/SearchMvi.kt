package com.codebox.speedrun.domain.dashboard.search

sealed class Intent {
    data class Search(val searchTerm: String) : Intent()
}

data class ViewState(
    val searchTerm: String = "",
)

package com.codebox.speedrun.domain.feature.game

sealed class Intent

data class ViewState(
    val gameName: String = "",
    val coverLargeUri: String? = null,
    val coverSmallUri: String? = null
)

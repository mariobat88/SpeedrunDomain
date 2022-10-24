package com.speedrun.domain.feature.leaderboards

sealed class Intent

data class ViewState(

    val developers: String = "",
    val publishers: String = "",
)

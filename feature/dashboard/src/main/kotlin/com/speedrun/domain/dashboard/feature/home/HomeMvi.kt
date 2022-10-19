package com.speedrun.domain.dashboard.feature.home

sealed class Intent

data class ViewState(
    val any: Any = Any()
)

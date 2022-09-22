package com.codebox.speedrun.domain.dashboard.home

sealed class Intent

data class ViewState(
    val any: Any = Any()
)

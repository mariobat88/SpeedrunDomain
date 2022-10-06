package com.codebox.speedrun.domain.feature.game

sealed class Intent

data class ViewState(
    val a: Any = Any()
)

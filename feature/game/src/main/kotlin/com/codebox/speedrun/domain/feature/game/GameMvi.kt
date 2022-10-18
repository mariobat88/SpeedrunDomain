package com.codebox.speedrun.domain.feature.game

sealed class Intent

data class ViewState(
    val gameName: String = "",
    val releaseDate: String = "",
    val coverLargeUri: String? = null,
    val coverSmallUri: String? = null,
    val showMilliseconds: Boolean = false,
    val requireVerification: Boolean = false,
    val requireVideo: Boolean = false,
    val runTimes: List<String> = emptyList(),
    val emulatorsAllowed: Boolean = false,
)

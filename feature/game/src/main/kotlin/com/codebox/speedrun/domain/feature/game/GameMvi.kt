package com.codebox.speedrun.domain.feature.game

import com.codebox.speedrun.domain.core.framework.async.Async
import com.codebox.speedrun.domain.core.framework.async.Uninitialized
import com.codebox.speedrun.domain.data.repo.games.model.GameModel

sealed class Intent

data class ViewState(
    val gameAsync: Async<GameModel> = Uninitialized,
//    val gameName: String = "",
//    val releaseDate: String = "",
//    val coverLargeUri: String? = null,
//    val coverSmallUri: String? = null,
//    val showMilliseconds: Boolean = false,
//    val requireVerification: Boolean = false,
//    val requireVideo: Boolean = false,
//    val runTimes: List<String> = emptyList(),
//    val emulatorsAllowed: Boolean = false,
)

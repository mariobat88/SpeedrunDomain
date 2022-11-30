package com.speedrun.domain.feature.player

import com.speedrun.domain.core.framework.async.Async
import com.speedrun.domain.core.framework.async.Loading
import com.speedrun.domain.repo.players.model.PlayerModel

sealed class Intent

data class ViewState(
    val playerAsync: Async<PlayerModel?> = Loading()
)

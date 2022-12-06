package com.speedrun.domain.feature.player

import com.speedrun.domain.core.framework.async.Async
import com.speedrun.domain.core.framework.async.Loading
import com.speedrun.domain.repo.games.model.GameModel
import com.speedrun.domain.repo.players.model.PlayerModel
import com.speedrun.domain.repo.players.model.RunPositionModel

sealed class Intent

data class ViewState(
    val playerAsync: Async<PlayerModel?> = Loading(),
    val runPositionsAsync: Async<Map<GameModel?, List<RunPositionModel>>> = Loading()
)

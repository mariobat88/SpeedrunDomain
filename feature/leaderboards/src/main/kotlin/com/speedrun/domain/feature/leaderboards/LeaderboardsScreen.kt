package com.speedrun.domain.feature.leaderboards

import androidx.compose.runtime.Composable
import com.speedrun.domain.core.framework.Screen
import kotlinx.coroutines.flow.MutableSharedFlow

@Composable
internal fun LeaderboardsScreen(
    gameId: String
) {
    val leaderboardsViewModel = LeaderboardsViewModel.create(gameId)
    LeaderboardsScreen(leaderboardsViewModel)
}


@Composable
fun LeaderboardsScreen(
    viewModel: LeaderboardsViewModel
) = Screen(viewModel) { viewState, intentChannel, _ ->
    LeaderboardsScreen(viewState, intentChannel)
}

@Composable
fun LeaderboardsScreen(
    viewState: ViewState,
    intentChannel: MutableSharedFlow<Intent>,
) {

}

package com.speedrun.domain.feature.player

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.speedrun.domain.core.framework.Screen
import com.speedrun.domain.core.framework.async.Loading
import com.speedrun.domain.core.framework.async.Success
import com.speedrun.domain.core.ui.SpeedrunScreen
import com.speedrun.domain.data.repo.players.model.PlayerModel
import com.speedrun.domain.feature.player.navigation.PlayerNavigator
import com.speedrun.domain.kit.player.ui.UserRow
import kotlinx.coroutines.flow.MutableSharedFlow

@Composable
fun PlayerScreen(
    playerNavigator: PlayerNavigator,
) {
    val playerViewModel = PlayerViewModel.create(playerNavigator)
    PlayerScreen(playerViewModel)
}


@Composable
fun PlayerScreen(
    viewModel: PlayerViewModel
) = Screen(viewModel) { viewState, intentChannel, _ ->
    PlayerScreen(viewState, intentChannel)
}

@Composable
fun PlayerScreen(
    viewState: ViewState,
    intentChannel: MutableSharedFlow<Intent>,
) {
    SpeedrunScreen(
        modifier = Modifier.fillMaxSize()
    ) { screenPadding ->
        Box(modifier = Modifier.padding(screenPadding)) {
            when (val playerAsync = viewState.playerAsync) {
                is Loading -> {

                }
                is Success -> {
                    val player = playerAsync()
                    if (player is PlayerModel.UserModel) {
                        UserRow(player = player)
                    }
                }
                else -> {

                }
            }
        }
    }
}

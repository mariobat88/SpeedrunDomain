package com.codebox.speedrun.domain.feature.game

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.codebox.speedrun.domain.code.ui.SpeedrunScreen
import com.ramcosta.composedestinations.annotation.Destination

const val GAME_SCREEN_ROUTE = "GameScreen"

@Destination(GAME_SCREEN_ROUTE)
@Composable
fun GameScreen() {
    GameScreen(hiltViewModel())
}

@Composable
fun GameScreen(
    viewModel: GameViewModel
) {
    SpeedrunScreen { screenPadding ->
        Box(
            modifier = Modifier.padding(screenPadding)
        ) {
            Text(
                text = "Game",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
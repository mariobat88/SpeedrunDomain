package com.speedrun.domain.kit.player.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import com.speedrun.domain.data.repo.players.model.PlayerModel

@OptIn(ExperimentalTextApi::class)
@Composable
fun PlayerName(
    player: PlayerModel,
    modifier: Modifier = Modifier,
) {
    val darkTheme = isSystemInDarkTheme()

    if (player is PlayerModel.UserModel) {
        val color = if (darkTheme) {
            player.nameStyle.color?.dark
        } else {
            player.nameStyle.color?.light
        }
        val colorFrom = if (darkTheme) {
            player.nameStyle.colorFrom?.dark
        } else {
            player.nameStyle.colorFrom?.light
        }
        val colorTo = if (darkTheme) {
            player.nameStyle.colorTo?.dark
        } else {
            player.nameStyle.colorTo?.light
        }
        Text(
            text = player.names.international,
            modifier = modifier,
            style = if (color != null) {
                TextStyle(color = Color(android.graphics.Color.parseColor(color)))
            } else {
                TextStyle(
                    brush = Brush.linearGradient(
                        0.0f to Color(android.graphics.Color.parseColor(colorFrom)),
                        1.0f to Color(android.graphics.Color.parseColor(colorTo))
                    ),
                )
            }
        )
    } else {
        with(player as PlayerModel.GuestModel) {
            Text(
                text = player.name,
                modifier = modifier,
            )
        }
    }
}

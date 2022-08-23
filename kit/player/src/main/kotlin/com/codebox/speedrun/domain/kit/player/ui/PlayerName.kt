package com.codebox.speedrun.domain.kit.player.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import com.codebox.speedrun.domain.data.repo.players.model.PlayerModel

@OptIn(ExperimentalTextApi::class)
@Composable
fun PlayerName(
    modifier: Modifier,
    player: PlayerModel,
) {
    if (player is PlayerModel.UserModel) {
        val color = player.nameStyle.color
        val colorFrom = player.nameStyle.colorFrom
        val colorTo = player.nameStyle.colorTo
        Text(
            text = player.names.international,
            modifier = modifier,
            style = if (color != null) {
                TextStyle(color = Color(android.graphics.Color.parseColor(color.light)))
            } else {
                TextStyle(
                    brush = Brush.linearGradient(
                        0.0f to Color(android.graphics.Color.parseColor(colorFrom?.light)),
                        1.0f to Color(android.graphics.Color.parseColor(colorTo?.light))
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

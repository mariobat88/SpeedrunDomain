package com.speedrun.domain.kit.player.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.speedrun.domain.repo.players.model.PlayerModel
import com.speedrun.domain.core.ui.R as UIResources
import com.speedrun.domain.kit.player.R as PlayerKitResources


@Composable
fun UserRow(
    player: PlayerModel.UserModel,
    modifier: Modifier = Modifier,
) {
    BaseUserRow(
        player = player,
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(PlayerKitResources.dimen.player_row_height))
            .then(modifier),
    )
}

@Composable
private fun BaseUserRow(
    player: PlayerModel.UserModel,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        PlayerImage(
            modifier = Modifier.size(50.dp),
            uri = player.image
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                PlayerName(
                    modifier = Modifier.wrapContentSize(),
                    player = player
                )
                Row(
                    modifier = Modifier.wrapContentSize()
                ) {
                    if (player.twitch != null) {
                        Image(
                            painter = painterResource(UIResources.drawable.icon_twitch_20),
                            contentDescription = "",
                            modifier = Modifier
                                .wrapContentHeight()
                                .width(25.dp),
                            contentScale = ContentScale.Inside
                        )
                    }
                    if (player.twitter != null) {
                        Image(
                            painter = painterResource(UIResources.drawable.icon_twitter_20),
                            contentDescription = "",
                            modifier = Modifier
                                .wrapContentHeight()
                                .width(25.dp),
                            contentScale = ContentScale.Inside
                        )
                    }
                    if (player.youtube != null) {
                        Image(
                            painter = painterResource(UIResources.drawable.icon_youtube_20),
                            contentDescription = "",
                            modifier = Modifier
                                .wrapContentHeight()
                                .width(25.dp),
                            contentScale = ContentScale.Inside
                        )
                    }
                }
            }
            Row(
                modifier = Modifier.wrapContentSize()
            ) {
                CountryFlag(
                    countryCode = player.location?.country?.code,
                    modifier = Modifier.wrapContentSize()
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = player.location?.country?.international
                        ?: "",
                    modifier = Modifier.wrapContentSize(),
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

private fun countryFlag(code: String) = code
    .uppercase()
    .split("")
    .filter { it.isNotBlank() }
    .map { it.codePointAt(0) + 0x1F1A5 }
    .joinToString("") { String(Character.toChars(it)) }

@Composable
fun CountryFlag(
    modifier: Modifier = Modifier,
    countryCode: String?
) {
    androidx.compose.material3.Text(
        text = countryFlag(countryCode ?: ""),
        modifier = modifier
    )
}
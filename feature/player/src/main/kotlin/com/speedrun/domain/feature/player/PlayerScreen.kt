package com.speedrun.domain.feature.player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.speedrun.domain.core.framework.Screen
import com.speedrun.domain.core.framework.async.Async
import com.speedrun.domain.core.framework.async.Loading
import com.speedrun.domain.core.framework.async.Success
import com.speedrun.domain.core.ui.SpeedrunScreen
import com.speedrun.domain.feature.player.navigation.PlayerNavigator
import com.speedrun.domain.kit.leaderboard.OrdinalPlace
import com.speedrun.domain.kit.leaderboard.Place
import com.speedrun.domain.kit.player.ui.UserRow
import com.speedrun.domain.kit.run.ui.System
import com.speedrun.domain.repo.games.model.GameModel
import com.speedrun.domain.repo.players.model.PlayerModel
import com.speedrun.domain.repo.players.model.RunPositionModel
import kotlinx.coroutines.flow.MutableSharedFlow
import com.speedrun.domain.core.designsystem.R as DesignSystemResources

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
                    SuccessState(
                        player = playerAsync(),
                        personalBestsAsync = viewState.runPositionsAsync,
                        intentChannel = intentChannel,
                    )
                }
                else -> {

                }
            }
        }
    }
}

@Composable
fun SuccessState(
    player: PlayerModel?,
    personalBestsAsync: Async<Map<GameModel?, List<RunPositionModel>>>,
    intentChannel: MutableSharedFlow<Intent>,
) {
    Column {
        if (player is PlayerModel.UserModel) {
            UserRow(player = player)
        }

        if (personalBestsAsync.isSuccess()) {
            val personalBests = personalBestsAsync()!!

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                personalBests.entries.forEach { map ->
                    val gameModel = map.key
                    val runPositions = map.value
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                            ) {
                                AsyncImage(
                                    model = gameModel?.assets?.coverMedium,
                                    contentDescription = "",
                                    modifier = Modifier.width(60.dp),
                                    contentScale = ContentScale.FillWidth
                                )
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight()
                                ) {
                                    Text(
                                        text = gameModel?.names?.international ?: "",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .wrapContentHeight()
                                            .padding(
                                                horizontal = dimensionResource(DesignSystemResources.dimen.side_padding),
                                                vertical = dimensionResource(DesignSystemResources.dimen.small_spacing),
                                            ),
                                        color = MaterialTheme.colorScheme.onBackground,
                                        style = MaterialTheme.typography.bodyLarge,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )

                                    runPositions.forEachIndexed { index, runPosition ->
                                        val background = if (index % 2 == 0) {
                                            MaterialTheme.colorScheme.secondary
                                        } else {
                                            MaterialTheme.colorScheme.tertiary
                                        }

                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .background(color = background)
                                                .padding(
                                                    horizontal = dimensionResource(
                                                        DesignSystemResources.dimen.side_padding
                                                    ),
                                                    vertical = dimensionResource(
                                                        DesignSystemResources.dimen.small_spacing
                                                    ),
                                                ),
                                            verticalAlignment = Alignment.CenterVertically,
                                        ) {
                                            Text(
                                                text = runPosition.runModel.category?.name ?: "",
                                                modifier = Modifier
                                                    .weight(1f)
                                                    .wrapContentHeight(),
                                                color = MaterialTheme.colorScheme.onBackground,
                                                style = MaterialTheme.typography.bodySmall,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                            Column(
                                                modifier = Modifier
                                                    .weight(1f)
                                                    .wrapContentHeight(),
                                                horizontalAlignment = Alignment.Start,
                                                verticalArrangement = Arrangement.SpaceEvenly
                                            ) {
                                                OrdinalPlace(
                                                    place = runPosition.place,
                                                    style = MaterialTheme.typography.bodyMedium,
                                                )
                                                Text(
                                                    text = toElapsedTime(runPosition.runModel.times.primary),
                                                    color = MaterialTheme.colorScheme.onBackground,
                                                    style = MaterialTheme.typography.bodyMedium,
                                                )
                                            }
                                            Column(
                                                modifier = Modifier
                                                    .weight(1f)
                                                    .wrapContentHeight(),
                                                horizontalAlignment = Alignment.Start,
                                                verticalArrangement = Arrangement.SpaceEvenly
                                            ) {
                                                System(
                                                    system = runPosition.runModel.system,
                                                    color = MaterialTheme.colorScheme.onBackground,
                                                    textAlign = TextAlign.Start,
                                                    style = MaterialTheme.typography.bodyMedium,
                                                )
                                                Text(
                                                    text = runPosition.runModel.date ?: "",
                                                    color = MaterialTheme.colorScheme.onBackground,
                                                    style = MaterialTheme.typography.bodyMedium,
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                            Spacer(
                                modifier = Modifier.height(
                                    dimensionResource(
                                        DesignSystemResources.dimen.side_padding
                                    )
                                )
                            )
                        }
                    }
                }
            }

        }
    }
}

fun toElapsedTime(playtime: String): String {
    val stripped = playtime.removePrefix("PT")
    return stripped.lowercase()
}
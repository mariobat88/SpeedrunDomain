package com.speedrun.domain.feature.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import com.speedrun.data.common.enums.RunTimeEnum
import com.speedrun.domain.core.designsystem.theme.SpeedrunColors
import com.speedrun.domain.core.framework.Screen
import com.speedrun.domain.core.framework.async.Loading
import com.speedrun.domain.core.framework.async.Success
import com.speedrun.domain.core.ui.SpeedrunScreen
import com.speedrun.domain.core.ui.Tile
import com.speedrun.domain.core.utils.capitalized
import com.speedrun.domain.kit.run.ui.Run
import kotlinx.coroutines.flow.MutableSharedFlow
import com.speedrun.domain.core.designsystem.R as DesignSystemResources
import com.speedrun.domain.feature.game.R as GameScreenResources

@Composable
internal fun GameScreen(
    gameId: String
) {
    val gameViewModel = GameViewModel.create(gameId)
    GameScreen(gameViewModel)
}

@Composable
private fun GameScreen(
    viewModel: GameViewModel
) = Screen(viewModel) { viewState, intentChannel, _ ->
    GameScreen(viewState, intentChannel)
}

@Composable
fun GameScreen(
    viewState: ViewState,
    intentChannel: MutableSharedFlow<Intent>,
) {
    SpeedrunScreen { screenPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    bottom = screenPadding.calculateBottomPadding(),
                )
        ) {
            item("Header") {
                Header(
                    viewState = viewState,
                    screenPadding = screenPadding,
                )
            }
            item("HeaderSpacer") {
                Spacer(
                    modifier = Modifier.height(dimensionResource(DesignSystemResources.dimen.side_padding))
                )
            }
            item("GameInfo") {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(
                            start = dimensionResource(DesignSystemResources.dimen.side_padding),
                            end = dimensionResource(DesignSystemResources.dimen.side_padding),
                        )
                ) {
                    GameInfo(viewState)
                }
            }
            item("GameInfoSpacer") {
                Spacer(
                    modifier = Modifier.height(dimensionResource(DesignSystemResources.dimen.side_padding))
                )
            }
            item("RuleSet") {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = dimensionResource(DesignSystemResources.dimen.side_padding))
                ) {
                    Tile(
                        title = stringResource(GameScreenResources.string.ruleset),
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(vertical = dimensionResource(DesignSystemResources.dimen.side_padding)),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Rule(
                                required = viewState.gameAsync()?.ruleset?.showMilliseconds,
                                title = stringResource(GameScreenResources.string.show_milliseconds),
                            )
                            Rule(
                                required = viewState.gameAsync()?.ruleset?.requireVerification,
                                title = stringResource(GameScreenResources.string.require_verification),
                            )
                            Rule(
                                required = viewState.gameAsync()?.ruleset?.requireVideo,
                                title = stringResource(GameScreenResources.string.require_video),
                            )
                            Rule(
                                required = viewState.gameAsync()?.ruleset?.emulatorsAllowed,
                                title = stringResource(GameScreenResources.string.emulators_allowed),
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                            ) {
                                viewState.gameAsync()?.ruleset?.runTimes?.forEachIndexed { index, runTime ->
                                    Runtime(
                                        runTime = runTime,
                                        defaultRuntime = viewState.gameAsync()?.ruleset?.defaultTime
                                    )
                                    if (viewState.gameAsync()?.ruleset?.runTimes?.lastIndex != index) {
                                        Spacer(modifier = Modifier.width(4.dp))
                                    }
                                }
                            }
                        }
                    }
                }
            }
            item("RuleSetSpacer") {
                Spacer(
                    modifier = Modifier.height(dimensionResource(DesignSystemResources.dimen.side_padding))
                )
            }
            if (viewState.runsAsync is Success) {
                item("Runs") {
                    Tile(
                        title = stringResource(GameScreenResources.string.latest_runs),
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(horizontal = dimensionResource(DesignSystemResources.dimen.side_padding))
                    )
                }
                items(viewState.runsAsync()?.size ?: 0) { index ->
                    val run = viewState.runsAsync()?.get(index)!!
                    key(run.id) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(horizontal = dimensionResource(DesignSystemResources.dimen.side_padding))
                                .background(MaterialTheme.colorScheme.tertiary)
                                .padding(vertical = 4.dp),
                        ) {
                            Run(
                                run = run,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                            )
                        }
                    }
                    Divider()
                }
            }
        }
    }
}

@Composable
private fun Header(
    viewState: ViewState,
    screenPadding: PaddingValues,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        when (val gameAsync = viewState.gameAsync) {
            is Loading -> {
                LinearProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            is Success -> {
                SubcomposeAsyncImage(
                    model = gameAsync().assets.coverLarge.uri,
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                ) {
                    val painter = painter
                    val state = painter.state
                    if (state is AsyncImagePainter.State.Success) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Image(
                                painter = painter,
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(SpeedrunColors.CoverOverlay),
                            )
                            ProvideTextStyle(
                                MaterialTheme.typography.headlineMedium
                            ) {
                                Text(
                                    text = gameAsync().names.international,
                                    modifier = Modifier
                                        .wrapContentSize()
                                        .align(Alignment.Center)
                                        .padding(
                                            horizontal = dimensionResource(
                                                DesignSystemResources.dimen.side_padding
                                            )
                                        ),
                                    color = MaterialTheme.colorScheme.onBackground,
                                    textAlign = TextAlign.Center,
                                )
                            }
                            IconButton(
                                onClick = {},
                                modifier = Modifier
                                    .padding(top = screenPadding.calculateTopPadding())
                                    .wrapContentSize()
                                    .align(Alignment.TopEnd)
                            ) {
                                Icon(
                                    painter = painterResource(GameScreenResources.drawable.ic_trophy),
                                    contentDescription = "leaderboards",
                                    tint = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        }
                    }
                }
            }
            else -> {}
        }
    }
}

@Composable
private fun GameInfo(
    viewState: ViewState
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        AsyncImage(
            model = viewState.gameAsync()?.assets?.coverSmall?.uri,
            contentDescription = "",
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .border(0.5.dp, Color.DarkGray, RoundedCornerShape(12.dp))
                .width(100.dp)
                .height(160.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(
            modifier = Modifier.width(dimensionResource(DesignSystemResources.dimen.side_padding))
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = stringResource(
                    GameScreenResources.string.release_date,
                    viewState.gameAsync()?.releaseDate ?: ""
                ),
                color = MaterialTheme.colorScheme.onBackground,
            )
            if (viewState.developersAsync() != null && viewState.developersAsync()!!.isNotEmpty()) {
                Text(
                    text = stringResource(
                        GameScreenResources.string.developed_by,
                        viewState.developers
                    ),
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
            if (viewState.publishersAsync() != null && viewState.publishersAsync()!!.isNotEmpty()) {
                Text(
                    text = stringResource(
                        GameScreenResources.string.developed_by,
                        viewState.publishers
                    ),
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        }
    }
}

@Composable
private fun Rule(
    required: Boolean?,
    title: String
) {
    Row {
        if (required == true) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary
            )
        } else if (required == false) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.error
            )
        }
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}

@Composable
private fun Runtime(
    runTime: RunTimeEnum,
    defaultRuntime: RunTimeEnum?
) {
    Row(
        modifier = Modifier.wrapContentSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = runTime == defaultRuntime,
            onClick = null,
            enabled = false,
            colors = RadioButtonDefaults.colors(
                disabledSelectedColor = MaterialTheme.colorScheme.primary,
                disabledUnselectedColor = MaterialTheme.colorScheme.primary,
            )
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = runTime.name.lowercase().capitalized(),
            color = MaterialTheme.colorScheme.onBackground,
        )
    }

}

@Preview
@Composable
fun PreviewGameScreen() {
    GameScreen(viewState = ViewState(), intentChannel = MutableSharedFlow())
}

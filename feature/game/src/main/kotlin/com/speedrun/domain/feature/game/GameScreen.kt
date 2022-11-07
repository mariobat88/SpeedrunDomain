package com.speedrun.domain.feature.game

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.speedrun.domain.core.ui.RoundedCornerBox
import com.speedrun.domain.core.ui.SpeedrunScreen
import com.speedrun.domain.core.ui.Tile
import com.speedrun.domain.core.utils.capitalized
import com.speedrun.domain.feature.game.navigation.GameNavigator
import kotlinx.coroutines.flow.MutableSharedFlow
import com.speedrun.domain.core.designsystem.R as DesignSystemResources
import com.speedrun.domain.feature.game.R as GameScreenResources

@Composable
internal fun GameScreen(
    gameId: String,
    gameNavigator: GameNavigator,
) {
    val gameViewModel = GameViewModel.create(gameId, gameNavigator)
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
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text(text = "") },
                    navigationIcon = {
                        IconButton(
                            onClick = { intentChannel.tryEmit(Intent.BackClicked) }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Navigate back"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = Color.Transparent,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                )
            },
            content = { padding ->
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
                            intentChannel = intentChannel
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
                }
            }
        )
    }
}

@Composable
private fun Header(
    viewState: ViewState,
    intentChannel: MutableSharedFlow<Intent>,
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
                    model = gameAsync().assets?.coverLarge,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(230.dp),
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
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .padding(horizontal = dimensionResource(DesignSystemResources.dimen.side_padding))
                        .align(Alignment.BottomCenter),
                ) {
                    Box(
                        modifier = Modifier
                            .width(100.dp)
                            .fillMaxHeight()
                    ) {
                        RoundedCornerBox(
                            borderWidth = 5.dp,
                            borderColor = MaterialTheme.colorScheme.background,
                        ) {
                            AsyncImage(
                                model = viewState.gameAsync()?.assets?.coverSmall,
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .align(Alignment.Bottom)
                    ) {
                        Button(
                            onClick = { intentChannel.tryEmit(Intent.LeaderboardsClicked) },
                            modifier = Modifier.align(Alignment.CenterEnd),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            ),
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(GameScreenResources.drawable.ic_trophy),
                                    contentDescription = "Leaderboards",
                                    modifier = Modifier.size(16.dp),
                                    tint = MaterialTheme.colorScheme.onPrimary
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(text = "Leaderboards")
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
        AnimatedVisibility(visible = viewState.developersAsync() != null && viewState.developersAsync()!!.isNotEmpty()) {
            Text(
                text = stringResource(
                    GameScreenResources.string.developed_by,
                    viewState.developers
                ),
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
        AnimatedVisibility(visible = viewState.publishersAsync() != null && viewState.publishersAsync()!!.isNotEmpty()) {
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

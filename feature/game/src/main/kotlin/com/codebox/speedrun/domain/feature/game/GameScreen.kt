package com.codebox.speedrun.domain.feature.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import com.codebox.speedrun.domain.code.ui.SpeedrunScreen
import com.codebox.speedrun.domain.core.framework.Screen
import com.codebox.speedrun.domain.designsystem.theme.SpeedrunColors
import kotlinx.coroutines.flow.MutableSharedFlow
import com.codebox.speedrun.domain.core.designsystem.R as DesignSystemResources
import com.codebox.speedrun.domain.feature.game.R as GameScreenResources

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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    bottom = screenPadding.calculateBottomPadding(),
                )
                .verticalScroll(rememberScrollState())
        ) {
            SubcomposeAsyncImage(
                model = viewState.coverLargeUri,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
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
                                text = viewState.gameName,
                                modifier = Modifier
                                    .wrapContentSize()
                                    .align(Alignment.Center)
                                    .padding(horizontal = dimensionResource(DesignSystemResources.dimen.side_padding)),
                                color = MaterialTheme.colorScheme.onBackground,
                                textAlign = TextAlign.Center,
                            )
                        }

                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(
                        start = dimensionResource(DesignSystemResources.dimen.side_padding),
                        end = dimensionResource(DesignSystemResources.dimen.side_padding),
                    )
            ) {
                Spacer(
                    modifier = Modifier.height(dimensionResource(DesignSystemResources.dimen.side_padding))
                )
                Text(
                    text = stringResource(
                        GameScreenResources.string.release_date,
                        viewState.releaseDate
                    ),
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Spacer(
                    modifier = Modifier.height(dimensionResource(DesignSystemResources.dimen.side_padding))
                )
                Text(
                    text = stringResource(GameScreenResources.string.ruleset),
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Divider(
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewGameScreen() {
    GameScreen(viewState = ViewState(), intentChannel = MutableSharedFlow())
}

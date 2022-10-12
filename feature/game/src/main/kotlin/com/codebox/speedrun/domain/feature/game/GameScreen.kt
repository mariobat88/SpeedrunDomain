package com.codebox.speedrun.domain.feature.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import com.codebox.speedrun.domain.code.ui.SpeedrunScreen
import com.codebox.speedrun.domain.core.framework.Screen
import com.codebox.speedrun.domain.designsystem.theme.SpeedrunColors

@Composable
fun GameScreen(
    gameId: String
) {
    val gameViewModel = GameViewModel.create(gameId)
    GameScreen(gameViewModel)
}

@Composable
fun GameScreen(
    viewModel: GameViewModel
) = Screen(viewModel) { viewState, intentChannel, _ ->
    SpeedrunScreen { screenPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = screenPadding.calculateBottomPadding())
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
                                .background(SpeedrunColors.CoverOverlay)
                        )
                        Text(
                            text = viewState.gameName,
                            modifier = Modifier
                                .wrapContentSize()
                                .align(Alignment.Center),
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
            AsyncImage(
                model = viewState.coverSmallUri,
                contentDescription = "",
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .border(0.5.dp, Color.DarkGray, RoundedCornerShape(12.dp))
                    .width(100.dp)
                    .height(160.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}
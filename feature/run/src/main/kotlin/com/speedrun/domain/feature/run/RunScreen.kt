package com.speedrun.domain.feature.run

import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import com.speedrun.domain.core.framework.Screen
import com.speedrun.domain.core.framework.async.Loading
import com.speedrun.domain.core.framework.async.Success
import com.speedrun.domain.core.ui.SpeedrunScreen
import com.speedrun.domain.data.repo.players.model.PlayerModel
import com.speedrun.domain.feature.run.navigation.RunNavigator
import com.speedrun.domain.kit.player.ui.UserRow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlin.time.Duration
import com.speedrun.domain.core.designsystem.R as DesignSystemResources
import com.speedrun.domain.feature.run.R as RunResources

@Composable
internal fun RunScreen(
    runNavigator: RunNavigator,
) {
    val gameViewModel = RunViewModel.create(runNavigator)
    RunScreen(gameViewModel)
}

@Composable
private fun RunScreen(
    viewModel: RunViewModel
) = Screen(viewModel) { viewState, intentChannel, _ ->
    RunScreen(viewState, intentChannel)
}

@Composable
internal fun RunScreen(
    viewState: ViewState,
    intentChannel: MutableSharedFlow<Intent>,
) {
    SpeedrunScreen(
        modifier = Modifier.fillMaxSize()
    ) { screenPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(screenPadding),
        ) {
            when (val leaderboardPlaceAsync = viewState.leaderboardPlaceAsync) {
                is Loading -> {

                }
                is Success -> {
                    val leaderboardPlace = leaderboardPlaceAsync()
                    val run = leaderboardPlace.run
                    AndroidView(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        factory = { context ->
                            val fm = (context as ComponentActivity).fragmentManager
                            val view = FrameLayout(context).apply {
                                id = RunResources.id.youtube_player
                            }
                            val fragment = YouTubePlayerFragment.newInstance().apply {
                                initialize(
                                    "AIzaSyCqa4Q0knwNdrjEepfENk-6ALgsofBDU2Y",
                                    object : YouTubePlayer.OnInitializedListener {
                                        override fun onInitializationSuccess(
                                            provider: YouTubePlayer.Provider?,
                                            player: YouTubePlayer?,
                                            wasRestored: Boolean
                                        ) {
                                            val video = run?.videos?.firstOrNull()
                                            video?.let { link ->
                                                val videoId = link.takeLast(11)
                                                player?.cueVideo(videoId)
                                            }

                                        }

                                        override fun onInitializationFailure(
                                            provider: YouTubePlayer.Provider?,
                                            result: YouTubeInitializationResult?
                                        ) {

                                        }
                                    })
                            }
                            val ft = fm.beginTransaction()
                            ft.add(RunResources.id.youtube_player, fragment)
                            ft.commit()
                            view
                        },
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(dimensionResource(DesignSystemResources.dimen.side_padding))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${run?.category?.name} in ${Duration.parseIsoString(run?.times?.primary ?: "")} - ",
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Box(
                                modifier = Modifier.size(20.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                val imageUri = when (leaderboardPlace.place) {
                                    1 -> run?.game?.assets?.trophy1st
                                    2 -> run?.game?.assets?.trophy2nd
                                    3 -> run?.game?.assets?.trophy3rd
                                    4 -> run?.game?.assets?.trophy4th
                                    else -> null
                                }
                                if (imageUri != null) {
                                    AsyncImage(
                                        model = imageUri,
                                        contentDescription = "",
                                        modifier = Modifier.fillMaxSize()
                                    )
                                } else {
                                    Text(
                                        text = leaderboardPlace.place.toString(),
                                        color = MaterialTheme.colorScheme.onBackground
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = stringResource(
                                    RunResources.string.place,
                                    toOrdinal(leaderboardPlace.place)
                                ),
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = stringResource(RunResources.string.run_by),
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        run?.players?.forEach { player ->
                            when (player) {
                                is PlayerModel.UserModel -> {
                                    UserRow(player)
                                }
                                is PlayerModel.GuestModel -> {

                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = stringResource(RunResources.string.notes),
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = run?.comment ?: "",
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = stringResource(RunResources.string.played_on),
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = run?.system?.platform?.name ?: "",
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
                else -> {

                }
            }
        }
    }
}

fun toOrdinal(number: Int): String {
    return when (number % 10) {
        1 -> "${number}st"
        2 -> "${number}nd"
        3 -> "${number}rd"
        else -> "${number}th"
    }
}
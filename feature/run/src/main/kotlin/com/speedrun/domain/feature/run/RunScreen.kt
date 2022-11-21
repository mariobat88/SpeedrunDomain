package com.speedrun.domain.feature.run

import android.util.Log
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import com.speedrun.domain.core.framework.Screen
import com.speedrun.domain.core.ui.SpeedrunScreen
import com.speedrun.domain.feature.run.navigation.RunNavigator
import kotlinx.coroutines.flow.MutableSharedFlow

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
        AndroidView(
            modifier = Modifier
                .fillMaxSize()
                .padding(screenPadding),
            factory = { context ->
                val fm = (context as ComponentActivity).fragmentManager
                val view = FrameLayout(context).apply {
                    id = R.id.youtube_player
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
                                Log.d("BATBAT", "onInitializationSuccess")
                                //viewState.runAsync()?.videos?.links?.getOrNull(0)?.let { link ->
                                player?.loadVideo("kdbRCZmzZpU")
                                player?.play()
                                //}
                            }

                            override fun onInitializationFailure(
                                provider: YouTubePlayer.Provider?,
                                result: YouTubeInitializationResult?
                            ) {
                                Log.d("BATBAT", result?.name ?: "")
                            }
                        })
                }
                val ft = fm.beginTransaction()
                ft.add(R.id.youtube_player, fragment)
                ft.commit()
                view
            },
        )
    }
}
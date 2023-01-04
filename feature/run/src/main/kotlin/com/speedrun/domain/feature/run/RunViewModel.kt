package com.speedrun.domain.feature.run

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.speedrun.domain.core.annotations.YoutubeApiKey
import com.speedrun.domain.core.framework.SpeedrunViewModel
import com.speedrun.domain.core.framework.async.Success
import com.speedrun.domain.core.wrapper.dispatchers.DispatcherProvider
import com.speedrun.domain.feature.run.navigation.RunNavigation
import com.speedrun.domain.feature.run.navigation.RunNavigator
import com.speedrun.domain.repo.leaderboards.LeaderboardsRepository
import com.speedrun.domain.repo.leaderboards.model.LeaderboardPlaceModel
import com.speedrun.domain.repo.players.PlayersRepository
import com.speedrun.domain.repo.players.model.PlayerModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class RunViewModel @AssistedInject constructor(
    @Assisted("savedStateHandle") private val savedStateHandle: SavedStateHandle,
    @Assisted("runNavigator") private val runNavigator: RunNavigator,
    @YoutubeApiKey private val youtubeApiKey: String,
    private val leaderboardsRepository: LeaderboardsRepository,
    private val playersRepository: PlayersRepository,
    dispatcherProvider: DispatcherProvider,
) : SpeedrunViewModel<ViewState, Intent, Unit>(
    viewState = ViewState(youtubeApiKey = youtubeApiKey)
) {

    @EntryPoint
    @InstallIn(ActivityComponent::class)
    interface ViewModelFactoryProvider {
        fun runViewModelFactory(): Factory
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("savedStateHandle") savedStateHandle: SavedStateHandle,
            @Assisted("runNavigator") runNavigator: RunNavigator,
        ): RunViewModel
    }

    companion object {
        @Composable
        fun create(
            runNavigator: RunNavigator,
        ): RunViewModel {
            val activity = LocalContext.current as Activity

            return viewModel(
                factory = object : ViewModelProvider.Factory {
                    @Suppress("UNCHECKED_CAST")
                    override fun <T : ViewModel> create(
                        modelClass: Class<T>,
                        extras: CreationExtras
                    ): T {
                        val entryPoint = EntryPointAccessors.fromActivity(
                            activity,
                            ViewModelFactoryProvider::class.java
                        )
                        val savedStateHandle = extras.createSavedStateHandle()
                        return entryPoint.runViewModelFactory()
                            .create(
                                savedStateHandle = savedStateHandle,
                                runNavigator = runNavigator,
                            ) as T
                    }
                })
        }
    }

    private val runId = savedStateHandle.get<String>(RunNavigation.runIdArg)!!
    private val simpleTimeFormatLong =
        SimpleDateFormat("yyyy-MM-dd'T'ss:mm:HH'Z'", Locale.getDefault())
    private val simpleTimeFormatShort = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    init {
        viewModelScope.launch(dispatcherProvider.main()) {
            val leaderboardPlace =
                leaderboardsRepository.observeLeaderboardPlace(runId).stateIn(this)
                    .asAsync()

            launch {
                leaderboardPlace
                    .collect { leaderboardPlaceAsync ->
                        val verifyDate = runCatching {
                            val longDate = simpleTimeFormatLong.parse(
                                leaderboardPlaceAsync()?.run?.status?.verifyDate ?: ""
                            )
                            simpleTimeFormatShort.format(longDate!!)
                        }.getOrNull()

                        _viewState.update {
                            it.copy(
                                leaderboardPlaceAsync = leaderboardPlaceAsync,
                                verifyDate = verifyDate
                            )
                        }
                    }
            }

            launch {
                leaderboardPlace
                    .filterIsInstance<Success<LeaderboardPlaceModel>>()
                    .mapNotNull { it().run?.status?.examiner }
                    .flatMapConcat { examiner ->
                        playersRepository.refreshPlayer(examiner)
                        playersRepository.observePlayer(examiner)
                    }.asAsync()
                    .filterIsInstance<Success<PlayerModel.UserModel>>()
                    .collect { examinerAsync ->
                        _viewState.update { it.copy(examinerAsync = examinerAsync) }
                    }

            }
        }
    }

    override suspend fun bind(intents: Flow<Intent>): Flow<Any> {
        return intents.filterIsInstance<Intent.PlayerClicked>()
            .onEach { runNavigator.navigateToPlayerScreen(it.playerId) }
    }
}

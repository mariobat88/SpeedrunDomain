package com.speedrun.domain.feature.game

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.speedrun.domain.core.framework.SpeedrunViewModel
import com.speedrun.domain.core.framework.async.Success
import com.speedrun.domain.core.wrapper.dispatchers.DispatcherProvider
import com.speedrun.domain.data.repo.developers.DevelopersRepository
import com.speedrun.domain.data.repo.games.GamesRepository
import com.speedrun.domain.data.repo.games.model.GameModel
import com.speedrun.domain.data.repo.publishers.PublishersRepository
import com.speedrun.domain.feature.game.navigation.GameNavigation
import com.speedrun.domain.feature.game.navigation.GameNavigator
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class GameViewModel @AssistedInject constructor(
    @Assisted("savedStateHandle") private val savedStateHandle: SavedStateHandle,
    @Assisted("gameNavigator") private val gameNavigator: GameNavigator,
    private val developersRepository: DevelopersRepository,
    private val publishersRepository: PublishersRepository,
    private val gamesRepository: GamesRepository,
    dispatcherProvider: DispatcherProvider,
) : SpeedrunViewModel<ViewState, Intent, Unit>(
    viewState = ViewState()
) {
    @EntryPoint
    @InstallIn(ActivityComponent::class)
    interface ViewModelFactoryProvider {
        fun gameViewModelFactory(): Factory
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("gameNavigator") gameNavigator: GameNavigator,
            @Assisted("savedStateHandle") savedStateHandle: SavedStateHandle,
        ): GameViewModel
    }

    companion object {
        @Composable
        fun create(
            gameNavigator: GameNavigator,
        ): GameViewModel {
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
                        return entryPoint.gameViewModelFactory()
                            .create(
                                savedStateHandle = savedStateHandle,
                                gameNavigator = gameNavigator,
                            ) as T
                    }
                })
        }
    }

    private val gameId = savedStateHandle.get<String>(GameNavigation.gameIdArg)!!

    init {
        viewModelScope.launch(dispatcherProvider.main()) {
            val getGameByIdFlow = gamesRepository.getGameById(gameId).stateIn(this)
                .asAsync()

            launch {
                getGameByIdFlow
                    .collect { gameAsync ->
                        reduce { it.copy(gameAsync = gameAsync) }
                    }
            }

            launch {
                getGameByIdFlow
                    .filterIsInstance<Success<GameModel>>()
                    .map { it.value }
                    .map { gameModel ->
                        gameModel.developers?.map { developerId ->
                            async { developersRepository.getDeveloper(developerId) }
                        }?.awaitAll()
                    }.asAsync()
                    .collect { developersAsync ->
                        reduce {
                            it.copy(
                                developersAsync = developersAsync,
                                developers = developersAsync()?.joinToString(", ") { it.name } ?: ""
                            )
                        }
                    }
            }

            launch {
                getGameByIdFlow
                    .filterIsInstance<Success<GameModel>>()
                    .map { it.value }
                    .map { gameModel ->
                        gameModel.publishers?.map { developerId ->
                            async { publishersRepository.getPublisher(developerId) }
                        }?.awaitAll()
                    }.asAsync()
                    .collect { publishersAsync ->
                        reduce {
                            it.copy(
                                publishersAsync = publishersAsync,
                                publishers = publishersAsync()?.map { it.name }?.joinToString(", ")
                                    ?: ""
                            )
                        }
                    }
            }
        }
    }

    override suspend fun bind(intents: Flow<Intent>): Flow<Any> {
        val backClickedIntent = intents.filterIsInstance<Intent.BackClicked>()
            .onEach { gameNavigator.backClicked() }

        val leaderboardsClickedIntent = intents.filterIsInstance<Intent.LeaderboardsClicked>()
            .onEach { gameNavigator.navigateToLeaderboardsScreen(gameId) }

        return merge(backClickedIntent, leaderboardsClickedIntent)
    }
}

package com.speedrun.domain.feature.game

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.speedrun.domain.core.framework.SpeedrunViewModel
import com.speedrun.domain.core.framework.async.Success
import com.speedrun.domain.core.wrapper.dispatchers.DispatcherProvider
import com.speedrun.domain.data.repo.developers.DevelopersRepository
import com.speedrun.domain.data.repo.games.GamesRepository
import com.speedrun.domain.data.repo.games.model.GameModel
import com.speedrun.domain.data.repo.publishers.PublishersRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GameViewModel @AssistedInject constructor(
    @Assisted("gameId") private val gameId: String,
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
            @Assisted("gameId") gameId: String,
        ): GameViewModel
    }

    companion object {
        @Composable
        fun create(
            gameId: String
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
                        return entryPoint.gameViewModelFactory()
                            .create(gameId) as T
                    }
                })
        }
    }

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
}
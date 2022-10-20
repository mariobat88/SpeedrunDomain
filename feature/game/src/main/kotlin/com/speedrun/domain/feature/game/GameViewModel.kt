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
import com.speedrun.domain.core.wrapper.dispatchers.DispatcherProvider
import com.speedrun.domain.data.repo.developers.DevelopersRepository
import com.speedrun.domain.data.repo.games.GamesRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GameViewModel @AssistedInject constructor(
    @Assisted("gameId") private val gameId: String,
    private val developersRepository: DevelopersRepository,
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

            launch {
                getGameByIdFlow
                    .asAsync()
                    .collect { gameAsync ->
                        reduce { it.copy(gameAsync = gameAsync) }
                    }
            }

            launch {
                getGameByIdFlow
                    .map { gameModel ->
                        gameModel.developers?.map { developerId ->
                            async { developersRepository.getDeveloper(developerId) }
                        }?.awaitAll()
                    }.asAsync()
                    .collect { developersAsync ->
                        reduce { it.copy(developersAsync = developersAsync) }
                    }
            }
        }
    }
}
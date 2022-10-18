package com.codebox.speedrun.domain.feature.game

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codebox.speedrun.domain.core.framework.SpeedrunViewModel
import com.codebox.speedrun.domain.data.repo.games.GamesRepository
import com.codebox.speedrun.domain.wrapper.dispatchers.DispatcherProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.launch

class GameViewModel @AssistedInject constructor(
    @Assisted("gameId") private val gameId: String,
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
            gamesRepository.getGameById(gameId)
                .asAsync()
                .collect { gameAsync -> reduce { it.copy(gameAsync = gameAsync) } }
        }
    }
}
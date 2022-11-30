package com.speedrun.domain.feature.player

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.speedrun.domain.core.framework.SpeedrunViewModel
import com.speedrun.domain.core.wrapper.dispatchers.DispatcherProvider
import com.speedrun.domain.feature.player.navigation.PlayerNavigation
import com.speedrun.domain.feature.player.navigation.PlayerNavigator
import com.speedrun.domain.repo.players.PlayersRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlayerViewModel @AssistedInject constructor(
    @Assisted("savedStateHandle") private val savedStateHandle: SavedStateHandle,
    @Assisted("playerNavigator") private val playerNavigator: PlayerNavigator,
    private val playersRepository: PlayersRepository,
    dispatcherProvider: DispatcherProvider,
) : SpeedrunViewModel<ViewState, Intent, Unit>(
    viewState = ViewState()
) {

    @EntryPoint
    @InstallIn(ActivityComponent::class)
    interface ViewModelFactoryProvider {
        fun playerViewModelFactory(): Factory
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("savedStateHandle") savedStateHandle: SavedStateHandle,
            @Assisted("playerNavigator") playerNavigator: PlayerNavigator,
        ): PlayerViewModel
    }

    companion object {
        @Composable
        fun create(
            playerNavigator: PlayerNavigator,
        ): PlayerViewModel {
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
                        return entryPoint.playerViewModelFactory()
                            .create(
                                savedStateHandle = savedStateHandle,
                                playerNavigator = playerNavigator,
                            ) as T
                    }
                })
        }
    }

    private val playerId = savedStateHandle.get<String>(PlayerNavigation.playerIdArg)!!

    init {
        viewModelScope.launch(dispatcherProvider.main()) {
            launch {
                playersRepository.refreshPlayer(playerId)
                playersRepository.observePlayer(playerId)
                    .asAsync()
                    .collect { playerAsync ->
                        _viewState.update {
                            it.copy(
                                playerAsync = playerAsync
                            )
                        }
                    }
            }
        }
    }
}
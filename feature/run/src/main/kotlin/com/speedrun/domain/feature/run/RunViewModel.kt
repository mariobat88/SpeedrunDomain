package com.speedrun.domain.feature.run

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.speedrun.domain.core.framework.SpeedrunViewModel
import com.speedrun.domain.core.wrapper.dispatchers.DispatcherProvider
import com.speedrun.domain.feature.run.navigation.RunNavigation
import com.speedrun.domain.feature.run.navigation.RunNavigator
import com.speedrun.domain.repo.runs.RunsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RunViewModel @AssistedInject constructor(
    @Assisted("savedStateHandle") private val savedStateHandle: SavedStateHandle,
    @Assisted("runNavigator") private val runNavigator: RunNavigator,
    private val runsRepository: RunsRepository,
    private val dispatcherProvider: DispatcherProvider,
) : SpeedrunViewModel<ViewState, Intent, Unit>(
    viewState = ViewState()
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

    init {
        viewModelScope.launch(dispatcherProvider.main()) {
            launch {
                runsRepository.observeRun(runId)
                    .asAsync()
                    .collect { runAsync ->
                        _viewState.update { it.copy(runAsync = runAsync) }
                    }
            }
        }
    }
}

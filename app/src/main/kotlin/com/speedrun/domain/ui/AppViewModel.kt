package com.speedrun.domain.ui

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.speedrun.domain.core.framework.navigation.StateNavigator
import com.speedrun.domain.core.framework.navigation.StateNavigatorImpl
import com.speedrun.domain.feature.game.navigation.GameNavigation
import com.speedrun.domain.navigation.AppNavigator
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ActivityComponent

class AppViewModel @AssistedInject constructor(
    @Assisted("stateNavigator") private val stateNavigator: StateNavigator,
) : ViewModel(), AppNavigator, StateNavigator by stateNavigator {

    @EntryPoint
    @InstallIn(ActivityComponent::class)
    interface ViewModelFactoryProvider {
        fun appViewModelFactory(): Factory
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("stateNavigator") stateNavigator: StateNavigator,
        ): AppViewModel
    }

    companion object {
        @Composable
        fun create(): AppViewModel {
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
                        return entryPoint.appViewModelFactory()
                            .create(StateNavigatorImpl()) as T
                    }
                })
        }
    }

    override fun navigateToGameScreen(gameId: String) {
        stateNavigator.navigateToRoute(GameNavigation(gameId))
    }
}

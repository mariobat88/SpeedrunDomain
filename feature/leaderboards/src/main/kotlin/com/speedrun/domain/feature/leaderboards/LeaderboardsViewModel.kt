package com.speedrun.domain.feature.leaderboards

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
import com.speedrun.domain.data.repo.categories.CategoriesRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.launch

class LeaderboardsViewModel @AssistedInject constructor(
    @Assisted("gameId") private val gameId: String,
    private val categoriesRepository: CategoriesRepository,
    dispatcherProvider: DispatcherProvider,
) : SpeedrunViewModel<ViewState, Intent, Unit>(
    viewState = ViewState()
) {
    @EntryPoint
    @InstallIn(ActivityComponent::class)
    interface ViewModelFactoryProvider {
        fun leaderboardsViewModelFactory(): Factory
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("gameId") gameId: String,
        ): LeaderboardsViewModel
    }

    companion object {
        @Composable
        fun create(
            gameId: String
        ): LeaderboardsViewModel {
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
                        return entryPoint.leaderboardsViewModelFactory()
                            .create(gameId) as T
                    }
                })
        }
    }

    init {
        viewModelScope.launch(dispatcherProvider.main()) {
            launch {
                suspend {
                    categoriesRepository.getCategoriesByGame(gameId)
                }.execute { categoriesAsync ->
                    reduce {
                        it.copy(
                            categoriesAsync = categoriesAsync
                        )
                    }
                }
            }
        }
    }
}

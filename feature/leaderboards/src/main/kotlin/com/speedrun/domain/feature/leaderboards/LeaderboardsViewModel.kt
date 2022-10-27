package com.speedrun.domain.feature.leaderboards

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.speedrun.data.common.enums.RunTypeEnum
import com.speedrun.domain.core.framework.SpeedrunViewModel
import com.speedrun.domain.core.wrapper.dispatchers.DispatcherProvider
import com.speedrun.domain.data.repo.categories.CategoriesRepository
import com.speedrun.domain.data.repo.leaderboards.LeaderboardsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class LeaderboardsViewModel @AssistedInject constructor(
    @Assisted("gameId") private val gameId: String,
    private val categoriesRepository: CategoriesRepository,
    private val leaderboardsRepository: LeaderboardsRepository,
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
                        .filter { it.type == RunTypeEnum.PER_GAME }
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

    override suspend fun bind(intents: Flow<Intent>): Flow<Any> {
        return intents.filterIsInstance<Intent.CategorySelected>()
            .map { intent ->
                val category = getViewState().categoriesAsync()!![intent.index]

                suspend {
                    leaderboardsRepository.refreshLeaderboards(gameId, category.id)
                }.execute {

                }

                leaderboardsRepository.getLeaderboard(gameId, category.id).asAsync()
                    .collect { leaderboardAsync ->
                        val leaderboardsMap = getViewState().leaderboardsMap
                        leaderboardsMap[intent.index] = leaderboardAsync

                        reduce {
                            it.copy(
                                leaderboardsMap = leaderboardsMap
                            )
                        }
                    }
            }
    }
}

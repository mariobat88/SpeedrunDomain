package com.speedrun.domain.feature.leaderboards

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.speedrun.data.common.enums.RunTypeEnum
import com.speedrun.domain.core.framework.SpeedrunViewModel
import com.speedrun.domain.core.wrapper.dispatchers.DispatcherProvider
import com.speedrun.domain.feature.leaderboards.navigation.LeaderboardNavigator
import com.speedrun.domain.feature.leaderboards.navigation.LeaderboardsNavigation
import com.speedrun.domain.repo.categories.CategoriesRepository
import com.speedrun.domain.repo.leaderboards.LeaderboardsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LeaderboardsViewModel @AssistedInject constructor(
    @Assisted("savedStateHandle") private val savedStateHandle: SavedStateHandle,
    @Assisted("leaderboardNavigator") private val leaderboardNavigator: LeaderboardNavigator,
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
            @Assisted("savedStateHandle") savedStateHandle: SavedStateHandle,
            @Assisted("leaderboardNavigator") leaderboardNavigator: LeaderboardNavigator,
        ): LeaderboardsViewModel
    }

    companion object {
        @Composable
        fun create(
            leaderboardNavigator: LeaderboardNavigator,
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
                        val savedStateHandle = extras.createSavedStateHandle()
                        return entryPoint.leaderboardsViewModelFactory()
                            .create(
                                savedStateHandle = savedStateHandle,
                                leaderboardNavigator = leaderboardNavigator,
                            ) as T
                    }
                })
        }
    }

    private val gameId = savedStateHandle.get<String>(LeaderboardsNavigation.gameIdArg)!!

    init {
        viewModelScope.launch(dispatcherProvider.main()) {
            launch {
                categoriesRepository.refreshCategoriesByGame(gameId)
                categoriesRepository.observeCategoriesByGame(gameId)
                    .map { it.filter { it.type == RunTypeEnum.PER_GAME } }
                    .asAsync()
                    .collect { categoriesAsync ->
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
        val categorySelected = intents.filterIsInstance<Intent.CategorySelected>()
            .map { intent ->
                val category = getViewState().categoriesAsync()!![intent.index]
                val leaderboardsMap = getViewState().leaderboardsMap
                val currentEntry = leaderboardsMap[intent.index]

                if (currentEntry == null) {
                    suspend {
                        leaderboardsRepository.refreshLeaderboards(gameId, category.id)
                    }.execute {

                    }

                    viewModelScope.launch {
                        leaderboardsRepository.observeLeaderboard(gameId, category.id)
                            .map {
                                it.copy(
                                    runs = it.runs.sortedBy { it.place }
                                )
                            }
                            .asAsync()
                            .collect { leaderboardAsync ->
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

        val runClicked = intents.filterIsInstance<Intent.RunClicked>()
            .onEach { intent ->
                intent.runId?.let { runId ->
                    leaderboardNavigator.navigateToRunScreen(runId)
                }
            }

        return merge(
            categorySelected,
            runClicked,
        )
    }
}

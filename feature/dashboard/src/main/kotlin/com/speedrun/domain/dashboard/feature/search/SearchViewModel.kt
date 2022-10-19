package com.speedrun.domain.dashboard.feature.search

import android.app.Activity
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.speedrun.domain.core.framework.SpeedrunViewModel
import com.speedrun.domain.core.paging.SpeedrunPagingSource
import com.speedrun.domain.dashboard.feature.search.navigation.SearchNavigator
import com.speedrun.domain.data.repo.games.GamesRepository
import com.speedrun.domain.data.repo.players.PlayersRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.flow.*

class SearchViewModel @AssistedInject constructor(
    @Assisted("searchNavigator") private val searchNavigator: SearchNavigator,
    private val gamesRepository: GamesRepository,
    private val playersRepository: PlayersRepository,
) : SpeedrunViewModel<ViewState, Intent, Unit>(
    viewState = ViewState()
) {

    @EntryPoint
    @InstallIn(ActivityComponent::class)
    interface ViewModelFactoryProvider {
        fun searchViewModelFactory(): Factory
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("searchNavigator") searchNavigator: SearchNavigator,
        ): SearchViewModel
    }

    companion object {
        @Composable
        fun create(
            searchNavigator: SearchNavigator
        ): SearchViewModel {
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
                        return entryPoint.searchViewModelFactory()
                            .create(searchNavigator) as T
                    }
                })
        }

        const val INITIAL_LOAD_SIZE = 40
    }

    private val tabEnums = ViewState.TAB.values()

    var searchTerm by mutableStateOf("")
        private set

    private var gameSearchTerm by mutableStateOf("")

    private var playerSearchTerm by mutableStateOf("")

    private val gameSearchFlow = snapshotFlow { gameSearchTerm }

    private val playerSearchFlow = snapshotFlow { playerSearchTerm }

    val searchGames = gameSearchFlow
        .debounce(500)
        .distinctUntilChanged()
        .mapLatest { searchTerm ->
            Pager(
                pagingSourceFactory = {
                    SpeedrunPagingSource { offset, max ->
                        gamesRepository.searchGames(searchTerm, offset, max)
                    }
                },
                config = PagingConfig(pageSize = 20, initialLoadSize = INITIAL_LOAD_SIZE)
            ).flow.cachedIn(viewModelScope)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyFlow()
        )

    val searchPlayers = playerSearchFlow
        .debounce(500)
        .distinctUntilChanged()
        .mapLatest { searchTerm ->
            Pager(
                pagingSourceFactory = {
                    SpeedrunPagingSource { offset, max ->
                        playersRepository.searchPlayers(searchTerm, offset, max)
                    }
                },
                config = PagingConfig(pageSize = 20, initialLoadSize = INITIAL_LOAD_SIZE)
            ).flow.cachedIn(viewModelScope)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyFlow()
        )

    override suspend fun bind(intents: Flow<Intent>): Flow<Any> {
        val searchIntent = intents.filterIsInstance<Intent.Search>()
            .onEach { intent ->
                if (viewState.value.selectedTab == ViewState.TAB.GAMES) {
                    gameSearchTerm = intent.searchTerm
                    searchTerm = gameSearchTerm
                } else {
                    playerSearchTerm = intent.searchTerm
                    searchTerm = playerSearchTerm
                }
            }

        val selectedTabIntent = intents.filterIsInstance<Intent.TabSelected>()
            .onEach { intent ->
                reduce { it.copy(selectedTab = tabEnums[intent.index]) }
                searchTerm = if (intent.index == ViewState.TAB.GAMES.ordinal) {
                    gameSearchTerm
                } else {
                    playerSearchTerm
                }
            }

        val navigateToGameScreenIntent = intents.filterIsInstance<Intent.NavigateToGameScreen>()
            .onEach { intent -> searchNavigator.navigateToGameScreen(intent.gameId) }

        return merge(
            searchIntent,
            selectedTabIntent,
            navigateToGameScreenIntent,
        )
    }
}

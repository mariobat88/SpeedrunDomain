package com.codebox.speedrun.domain.dashboard.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.codebox.speedrun.domain.core.framework.SpeedrunViewModel
import com.codebox.speedrun.domain.core.framework.navigation.StateNavigator
import com.codebox.speedrun.domain.core.paging.SpeedrunPagingSource
import com.codebox.speedrun.domain.data.repo.games.GamesRepository
import com.codebox.speedrun.domain.data.repo.players.PlayersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val gamesRepository: GamesRepository,
    private val playersRepository: PlayersRepository,
    private val speedrunNavigator: StateNavigator,
) : SpeedrunViewModel<ViewState, Intent, Unit>(
    viewState = ViewState()
) {

    private val tabEnums = ViewState.TAB.values()

    var searchTerm by mutableStateOf("")
        private set

    var gameSearchTerm by mutableStateOf("")
        private set

    var playerSearchTerm by mutableStateOf("")
        private set

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

        return merge(
            searchIntent,
            selectedTabIntent
        )
    }

    companion object {
        const val INITIAL_LOAD_SIZE = 40
    }
}
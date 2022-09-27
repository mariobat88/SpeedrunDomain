package com.codebox.speedrun.domain.dashboard.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.viewModelScope
import com.codebox.speedrun.domain.core.framework.SpeedrunViewModel
import com.codebox.speedrun.domain.data.repo.games.GamesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val gamesRepository: GamesRepository,
) : SpeedrunViewModel<ViewState, Intent, Unit>(
    viewState = ViewState()
) {

    var searchTerm by mutableStateOf("")
        private set

    val search = snapshotFlow { searchTerm }
        .debounce(500)
        .mapLatest { gamesRepository.searchGame(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    override suspend fun bind(intents: Flow<Intent>): Flow<Any> {
        val searchIntent = intents.filterIsInstance<Intent.Search>()
            .onEach { intent -> searchTerm = intent.searchTerm }

        val selectedTabIntent = intents.filterIsInstance<Intent.TabSelected>()
            .onEach { intent -> reduce { it.copy(selectedTabIndex = intent.index) } }

        return merge(
            searchIntent,
            selectedTabIntent
        )
    }
}
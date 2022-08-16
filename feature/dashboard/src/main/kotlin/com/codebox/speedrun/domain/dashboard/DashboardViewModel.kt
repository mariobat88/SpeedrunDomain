package com.codebox.speedrun.domain.dashboard

import androidx.lifecycle.viewModelScope
import com.codebox.speedrun.domain.core.framework.SpeedrunViewModel
import com.codebox.speedrun.domain.repo.runs.RunsRepository
import com.codebox.speedrun.domain.wrapper.dispatchers.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val runsRepository: RunsRepository,
    dispatcherProvider: DispatcherProvider,
) : SpeedrunViewModel<ViewState, Intent, Unit>(
    viewState = ViewState()
) {

    init {
        viewModelScope.launch(dispatcherProvider.main()) {
            val latestRuns = runsRepository.getLatestVerifiedRuns()
            val groupedRuns = latestRuns.groupBy { it.game.id }.map { (gameId, runs) ->
                LatestRun(runs.find { it.game.id == gameId }!!.game, runs)
            }
            reduce { it.copy(latestRuns = groupedRuns) }
        }
    }
}
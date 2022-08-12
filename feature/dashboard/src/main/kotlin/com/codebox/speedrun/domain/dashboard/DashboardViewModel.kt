package com.codebox.speedrun.domain.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codebox.speedrun.domain.repo.runs.RunsRepository
import com.codebox.speedrun.domain.wrapper.dispatchers.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val runsRepository: RunsRepository,
    dispatcherProvider: DispatcherProvider,
) : ViewModel() {

    init {
        viewModelScope.launch(dispatcherProvider.main()) {
            val unit = runsRepository.getLatestVerifiedRuns()
        }
    }
}
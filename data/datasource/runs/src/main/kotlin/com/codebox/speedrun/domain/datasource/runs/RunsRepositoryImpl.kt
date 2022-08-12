package com.codebox.speedrun.domain.datasource.runs

import com.codebox.speedrun.domain.api.runs.RunsApiService
import com.codebox.speedrun.domain.repo.runs.RunsRepository
import com.codebox.speedrun.domain.wrapper.dispatchers.DispatcherProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RunsRepositoryImpl @Inject constructor(
    private val runsApiService: RunsApiService,
    private val dispatcherProvider: DispatcherProvider
) : RunsRepository {
    override fun getLatestVerifiedRuns() {
        Unit
    }
}
package com.speedrun.domain.datasource.runs

import com.speedrun.domain.api.runs.RunsApiService
import com.speedrun.domain.core.wrapper.dispatchers.DispatcherProvider
import com.speedrun.domain.datasource.runs.mapper.toModel
import com.speedrun.domain.repo.runs.RunsRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RunsRepositoryImpl @Inject constructor(
    private val runsApiService: RunsApiService,
    private val dispatcherProvider: DispatcherProvider
) : RunsRepository {
    override suspend fun getLatestVerifiedRuns() = withContext(dispatcherProvider.io()) {
         runsApiService.getLatestVerifiedRuns().data.map { it.toModel() }
    }
}

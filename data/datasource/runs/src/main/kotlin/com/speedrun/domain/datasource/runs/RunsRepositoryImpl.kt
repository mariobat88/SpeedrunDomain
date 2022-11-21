package com.speedrun.domain.datasource.runs

import com.speedrun.domain.api.runs.RunsApiService
import com.speedrun.domain.core.wrapper.dispatchers.DispatcherProvider
import com.speedrun.domain.data.database.SpeedrunDatabase
import com.speedrun.domain.datasource.runs.mapper.toModel
import com.speedrun.domain.datasource.runs.mapper.toRunModel
import com.speedrun.domain.repo.runs.RunsRepository
import com.speedrun.domain.repo.runs.model.RunModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RunsRepositoryImpl @Inject constructor(
    private val runsApiService: RunsApiService,
    private val dispatcherProvider: DispatcherProvider,
    speedrunDatabase: SpeedrunDatabase,
) : RunsRepository {

    private val runDao = speedrunDatabase.runDao()

    override suspend fun observeRun(runId: String): Flow<RunModel> =
        withContext(dispatcherProvider.io()) {
            runDao.observeRun(runId).map { it.toRunModel() }
        }

    override suspend fun getLatestVerifiedRuns(): List<RunModel> =
        withContext(dispatcherProvider.io()) {
            runsApiService.getLatestVerifiedRuns().data.map { it.toModel() }
        }

    override suspend fun getLatestRunsOfGame(gameId: String): List<RunModel> =
        withContext(dispatcherProvider.io()) {
            runsApiService.getLatestRunsOfGame(gameId).data.map { it.toModel() }
        }
}

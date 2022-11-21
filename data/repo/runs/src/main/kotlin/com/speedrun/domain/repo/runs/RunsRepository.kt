package com.speedrun.domain.repo.runs

import com.speedrun.domain.repo.runs.model.RunModel
import kotlinx.coroutines.flow.Flow

interface RunsRepository {

    suspend fun observeRun(runId: String): Flow<RunModel>

    suspend fun getLatestVerifiedRuns(): List<RunModel>

    suspend fun getLatestRunsOfGame(gameId: String): List<RunModel>
}

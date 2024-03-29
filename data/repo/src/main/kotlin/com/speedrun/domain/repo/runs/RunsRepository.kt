package com.speedrun.domain.repo.runs

import com.speedrun.domain.repo.runs.model.RunModel

interface RunsRepository {

    suspend fun getLatestVerifiedRuns(): List<RunModel>

    suspend fun getLatestRunsOfGame(gameId: String): List<RunModel>
}

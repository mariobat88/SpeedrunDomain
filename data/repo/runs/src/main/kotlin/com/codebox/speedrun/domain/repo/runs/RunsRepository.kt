package com.codebox.speedrun.domain.repo.runs

import com.codebox.speedrun.domain.repo.runs.model.RunModel

interface RunsRepository {

    suspend fun getLatestVerifiedRuns() : List<RunModel>
}

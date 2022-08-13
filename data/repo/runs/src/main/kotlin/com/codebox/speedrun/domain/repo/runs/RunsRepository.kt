package com.codebox.speedrun.domain.repo.runs

interface RunsRepository {

    suspend fun getLatestVerifiedRuns()
}

package com.speedrun.domain.api.runs

import retrofit2.http.GET
import retrofit2.http.Query

interface RunsApiService {

    @GET("runs?status=verified&orderby=verify-date&direction=desc&embed=category,game,players")
    suspend fun getLatestVerifiedRuns(): RunResponse

    @GET("runs?orderby=submitted&embed=category,game,players")
    suspend fun getLatestRunsOfGame(@Query("game") gameId: String): RunResponse
}

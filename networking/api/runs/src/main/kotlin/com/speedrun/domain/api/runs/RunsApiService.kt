package com.speedrun.domain.api.runs

import com.speedrun.domain.api.runs.models.DataRunResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RunsApiService {

    @GET("runs?status=verified&orderby=verify-date&direction=desc&embed=game,players")
    suspend fun getLatestVerifiedRuns(): DataRunResponse

    @GET("runs?orderby=submitted&embed=game,players")
    suspend fun getLatestRunsOfGame(@Query("game") gameId: String): DataRunResponse
}

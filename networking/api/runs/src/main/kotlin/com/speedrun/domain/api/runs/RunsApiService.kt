package com.speedrun.domain.api.runs

import retrofit2.http.GET

interface RunsApiService {

    @GET("runs?status=verified&orderby=verify-date&direction=desc&embed=category,game,players")
    suspend fun getLatestVerifiedRuns() : RunResponse
}

package com.codebox.speedrun.domain.api.runs

import retrofit2.http.GET

interface RunsApiService {

    @GET("runs?status=verified&orderby=verify-date&direction=desc")
    suspend fun getLatestVerifiedRuns()
}
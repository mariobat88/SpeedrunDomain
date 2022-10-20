package com.speedrun.domain.networking.api.developers

import retrofit2.http.GET
import retrofit2.http.Path

interface DevelopersApiService {

    @GET("developers/{id}")
    suspend fun getDeveloper(@Path("id") id: String): DeveloperResponse
}

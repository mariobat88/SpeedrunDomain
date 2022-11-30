package com.speedrun.domain.api.developers

import com.speedrun.domain.api.developers.models.DeveloperResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DevelopersApiService {

    @GET("developers/{id}")
    suspend fun getDeveloper(@Path("id") id: String): DeveloperResponse
}

package com.speedrun.domain.network.api.publishers

import retrofit2.http.GET
import retrofit2.http.Path

interface PublishersApiService {

    @GET("publishers/{id}")
    suspend fun getPublisher(@Path("id") id: String): PublisherResponse
}

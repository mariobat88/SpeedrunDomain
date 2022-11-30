package com.speedrun.domain.api.publishers

import com.speedrun.domain.api.publishers.models.PublisherResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PublishersApiService {

    @GET("publishers/{id}")
    suspend fun getPublisher(@Path("id") id: String): PublisherResponse
}

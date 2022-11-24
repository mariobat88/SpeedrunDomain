package com.speedrun.domain.networking.api.categories

import retrofit2.http.GET
import retrofit2.http.Path

interface CategoriesApiService {

    @GET("games/{gameId}/categories")
    suspend fun getCategoriesByGame(@Path("gameId") id: String): CategoriesResponse
}

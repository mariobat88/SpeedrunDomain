package com.speedrun.domain.api.categories

import com.speedrun.domain.api.categories.models.CategoryDataListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CategoriesApiService {

    @GET("games/{gameId}/categories")
    suspend fun getCategoriesByGame(@Path("gameId") id: String): CategoryDataListResponse
}

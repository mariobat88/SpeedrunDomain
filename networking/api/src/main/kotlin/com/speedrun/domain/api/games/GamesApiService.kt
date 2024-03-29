package com.speedrun.domain.api.games

import com.speedrun.domain.api.games.models.GameResponse
import com.speedrun.domain.api.pagination.models.PaginationResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface GamesApiService {
    @GET("games?embed=platforms")
    suspend fun searchGames(
        @Header("Cache-Control") cacheControl: String? = "no-cache",
        @Query("name") name: String,
        @Query("offset") offset: Int,
        @Query("max") max: Int
    ): PaginationResponse<GameResponse>
}

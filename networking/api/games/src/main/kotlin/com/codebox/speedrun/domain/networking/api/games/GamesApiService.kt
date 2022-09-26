package com.codebox.speedrun.domain.networking.api.games

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface GamesApiService {
    @GET("games")
    suspend fun searchGames(
        @Header("Cache-Control") cacheControl: String? = "no-cache",
        @Query("name") name: String
    ): SearchGameResponse
}

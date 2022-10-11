package com.codebox.speedrun.domain.networking.api.players

import com.codebox.speedrun.domain.networking.api.pagination.PaginationResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PlayersApiService {
    @GET("users")
    suspend fun searchPlayers(
        @Header("Cache-Control") cacheControl: String? = "no-cache",
        @Query("name") name: String,
        @Query("offset") offset: Int,
        @Query("max") max: Int
    ): PaginationResponse<UserResponse>
}
package com.speedrun.domain.api.players

import com.speedrun.domain.api.pagination.models.PaginationResponse
import com.speedrun.domain.api.players.models.PolymorphicPlayerResponse
import com.speedrun.domain.api.players.models.UserDataResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface PlayersApiService {
    @GET("users")
    suspend fun searchPlayers(
        @Header("Cache-Control") cacheControl: String? = "no-cache",
        @Query("name") name: String,
        @Query("offset") offset: Int,
        @Query("max") max: Int
    ): PaginationResponse<PolymorphicPlayerResponse.UserResponse>

    @GET("users/{playerId}")
    suspend fun getPlayer(
        @Path("playerId") playerId: String,
    ): UserDataResponse
}

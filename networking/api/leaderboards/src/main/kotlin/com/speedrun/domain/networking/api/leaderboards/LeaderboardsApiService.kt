package com.speedrun.domain.networking.api.leaderboards

import retrofit2.http.GET
import retrofit2.http.Path

interface LeaderboardsApiService {

    @GET("leaderboards/{gameId}/category/{categoryId}?embed=players,category,variables")
    suspend fun getLeaderboard(
        @Path("gameId") gameId: String,
        @Path("categoryId") categoryId: String,
    ): LeaderboardResponse
}

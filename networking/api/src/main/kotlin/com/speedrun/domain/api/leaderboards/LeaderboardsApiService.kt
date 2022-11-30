package com.speedrun.domain.api.leaderboards

import com.speedrun.domain.api.leaderboards.models.LeaderboardResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface LeaderboardsApiService {

    @GET("leaderboards/{gameId}/category/{categoryId}?embed=players,category,variables")
    suspend fun getLeaderboard(
        @Path("gameId") gameId: String,
        @Path("categoryId") categoryId: String,
    ): LeaderboardResponse
}

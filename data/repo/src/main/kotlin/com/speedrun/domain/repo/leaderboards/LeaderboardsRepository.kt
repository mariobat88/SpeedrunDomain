package com.speedrun.domain.repo.leaderboards

import com.speedrun.domain.repo.leaderboards.model.LeaderboardModel
import com.speedrun.domain.repo.leaderboards.model.LeaderboardPlaceModel
import kotlinx.coroutines.flow.Flow

interface LeaderboardsRepository {

    suspend fun refreshLeaderboards(gameId: String, categoryId: String)
    suspend fun observeLeaderboard(gameId: String, categoryId: String): Flow<LeaderboardModel>
    suspend fun observeLeaderboardPlace(runId: String): Flow<LeaderboardPlaceModel>
}

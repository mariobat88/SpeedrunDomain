package com.speedrun.domain.data.repo.leaderboards

import com.speedrun.domain.data.repo.leaderboards.model.LeaderboardModel
import kotlinx.coroutines.flow.Flow

interface LeaderboardsRepository {

    suspend fun refreshLeaderboards(gameId: String, categoryId: String)
    suspend fun getLeaderboard(gameId: String, categoryId: String): Flow<LeaderboardModel>
}

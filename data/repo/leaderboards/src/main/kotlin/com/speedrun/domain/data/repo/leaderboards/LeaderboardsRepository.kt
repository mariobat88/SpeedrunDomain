package com.speedrun.domain.data.repo.leaderboards

import com.speedrun.domain.data.repo.leaderboards.model.LeaderboardModel

interface LeaderboardsRepository {

    suspend fun getLeaderboard(gameId: String, categoryId: String): LeaderboardModel
}

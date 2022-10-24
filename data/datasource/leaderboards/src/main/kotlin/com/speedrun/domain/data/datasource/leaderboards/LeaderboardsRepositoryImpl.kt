package com.speedrun.domain.data.datasource.leaderboards

import com.speedrun.domain.core.wrapper.dispatchers.DispatcherProvider
import com.speedrun.domain.data.datasource.leaderboards.mapper.toLeaderboardModel
import com.speedrun.domain.data.repo.leaderboards.LeaderboardsRepository
import com.speedrun.domain.data.repo.leaderboards.model.LeaderboardModel
import com.speedrun.domain.networking.api.leaderboards.LeaderboardsApiService
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LeaderboardsRepositoryImpl @Inject constructor(
    private val leaderboardsApiService: LeaderboardsApiService,
    private val dispatcherProvider: DispatcherProvider,
) : LeaderboardsRepository {

    override suspend fun getLeaderboard(gameId: String, categoryId: String): LeaderboardModel =
        withContext(dispatcherProvider.io()) {
            val response = leaderboardsApiService.getLeaderboard(gameId, categoryId)
            response.data.toLeaderboardModel()
        }
}

package com.speedrun.domain.data.datasource.leaderboards.di

import com.speedrun.domain.data.datasource.leaderboards.LeaderboardsRepositoryImpl
import com.speedrun.domain.data.repo.leaderboards.LeaderboardsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface GamesRepositoryModule {
    @Binds
    fun bindGamesRepository(impl: LeaderboardsRepositoryImpl): LeaderboardsRepository
}

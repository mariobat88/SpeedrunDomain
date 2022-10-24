package com.speedrun.domain.networking.api.leaderboards.di

import com.speedrun.domain.networking.api.leaderboards.LeaderboardsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LeaderboardsApiModule {
    @Provides
    @Singleton
    fun provideLeaderboardsApiService(
        retrofit: Retrofit
    ): LeaderboardsApiService = retrofit.create(LeaderboardsApiService::class.java)
}

package com.speedrun.domain.api.di

import com.speedrun.domain.api.runs.RunsApiService
import com.speedrun.domain.api.categories.CategoriesApiService
import com.speedrun.domain.api.developers.DevelopersApiService
import com.speedrun.domain.api.games.GamesApiService
import com.speedrun.domain.api.leaderboards.LeaderboardsApiService
import com.speedrun.domain.api.publishers.PublishersApiService
import com.speedrun.domain.api.players.PlayersApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApisModule {
    @Provides
    @Singleton
    fun provideCategoriesApiService(
        retrofit: Retrofit
    ): CategoriesApiService = retrofit.create(CategoriesApiService::class.java)

    @Provides
    @Singleton
    fun provideDevelopersApiService(
        retrofit: Retrofit
    ): DevelopersApiService = retrofit.create(DevelopersApiService::class.java)

    @Provides
    @Singleton
    fun provideGamesApiService(
        retrofit: Retrofit
    ): GamesApiService = retrofit.create(GamesApiService::class.java)

    @Provides
    @Singleton
    fun provideLeaderboardsApiService(
        retrofit: Retrofit
    ): LeaderboardsApiService = retrofit.create(LeaderboardsApiService::class.java)

    @Provides
    @Singleton
    fun providePlayersApiService(
        retrofit: Retrofit
    ): PlayersApiService = retrofit.create(PlayersApiService::class.java)

    @Provides
    @Singleton
    fun providePublishersApiService(
        retrofit: Retrofit
    ): PublishersApiService = retrofit.create(PublishersApiService::class.java)

    @Provides
    @Singleton
    fun provideRunsApiService(
        retrofit: Retrofit
    ): RunsApiService = retrofit.create(RunsApiService::class.java)
}

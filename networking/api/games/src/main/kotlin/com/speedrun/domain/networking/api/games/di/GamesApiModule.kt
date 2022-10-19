package com.speedrun.domain.networking.api.games.di

import com.speedrun.domain.networking.api.games.GamesApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object GamesApiModule {
    @Provides
    @Singleton
    fun provideGamesApiService(
        retrofit: Retrofit
    ): GamesApiService = retrofit.create(GamesApiService::class.java)
}

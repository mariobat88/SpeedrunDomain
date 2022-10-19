package com.speedrun.domain.networking.api.players.di

import com.speedrun.domain.networking.api.players.PlayersApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object PlayersApiModule {
    @Provides
    @Singleton
    fun provideGamesApiService(
        retrofit: Retrofit
    ): PlayersApiService = retrofit.create(PlayersApiService::class.java)
}

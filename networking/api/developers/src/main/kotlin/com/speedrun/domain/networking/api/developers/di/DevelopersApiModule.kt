package com.speedrun.domain.networking.api.developers.di

import com.speedrun.domain.networking.api.developers.DevelopersApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DevelopersApiModule {
    @Provides
    @Singleton
    fun provideDevelopersApiService(
        retrofit: Retrofit
    ): DevelopersApiService = retrofit.create(DevelopersApiService::class.java)
}

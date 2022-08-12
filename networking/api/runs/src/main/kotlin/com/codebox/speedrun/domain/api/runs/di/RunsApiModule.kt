package com.codebox.speedrun.domain.api.runs.di

import com.codebox.speedrun.domain.api.runs.RunsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RunsApiModule {
    @Provides
    @Singleton
    fun provideRunsApiService(
        retrofit: Retrofit
    ): RunsApiService = retrofit.create(RunsApiService::class.java)
}

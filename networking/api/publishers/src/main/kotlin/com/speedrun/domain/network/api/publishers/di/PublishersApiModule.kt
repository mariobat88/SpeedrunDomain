package com.speedrun.domain.network.api.publishers.di

import com.speedrun.domain.network.api.publishers.PublishersApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object PublishersApiModule {
    @Provides
    @Singleton
    fun providePublishersApiService(
        retrofit: Retrofit
    ): PublishersApiService = retrofit.create(PublishersApiService::class.java)
}

package com.speedrun.domain.networking.api.categories.di

import com.speedrun.domain.networking.api.categories.CategoriesApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CategoriesApiModule {
    @Provides
    @Singleton
    fun provideCategoriesApiService(
        retrofit: Retrofit
    ): CategoriesApiService = retrofit.create(CategoriesApiService::class.java)
}

package com.speedrun.domain.data.datasource.categories.di

import com.speedrun.domain.data.datasource.categories.CategoriesRepositoryImpl
import com.speedrun.domain.data.repo.categories.CategoriesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface CategoriesRepositoryModule {
    @Binds
    fun bindCategoriesRepository(impl: CategoriesRepositoryImpl): CategoriesRepository
}

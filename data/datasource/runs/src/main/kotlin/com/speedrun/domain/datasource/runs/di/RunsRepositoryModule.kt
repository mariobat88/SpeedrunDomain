package com.speedrun.domain.datasource.runs.di

import com.speedrun.domain.datasource.runs.RunsRepositoryImpl
import com.speedrun.domain.repo.runs.RunsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface RunsRepositoryModule {
    @Binds
    fun bindRunsRepository(impl: RunsRepositoryImpl): RunsRepository
}
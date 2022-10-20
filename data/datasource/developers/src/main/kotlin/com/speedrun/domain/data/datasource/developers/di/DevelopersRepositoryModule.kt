package com.speedrun.domain.data.datasource.developers.di

import com.speedrun.domain.data.datasource.developers.DevelopersRepositoryImpl
import com.speedrun.domain.data.repo.developers.DevelopersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface DevelopersRepositoryModule {
    @Binds
    fun bindDevelopersRepository(impl: DevelopersRepositoryImpl): DevelopersRepository
}

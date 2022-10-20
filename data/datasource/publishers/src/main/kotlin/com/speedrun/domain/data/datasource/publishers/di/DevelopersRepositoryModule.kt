package com.speedrun.domain.data.datasource.publishers.di

import com.speedrun.domain.data.datasource.publishers.PublishersRepositoryImpl
import com.speedrun.domain.data.repo.publishers.PublishersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface PublishersRepositoryModule {
    @Binds
    fun bindPublishersRepository(impl: PublishersRepositoryImpl): PublishersRepository
}

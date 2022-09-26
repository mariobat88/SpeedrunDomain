package com.codebox.speedrun.domain.data.datasource.games.di

import com.codebox.speedrun.domain.data.datasource.games.GamesRepositoryImpl
import com.codebox.speedrun.domain.data.repo.games.GamesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface GamesRepositoryModule {
    @Binds
    fun bindGamesRepository(impl: GamesRepositoryImpl): GamesRepository
}

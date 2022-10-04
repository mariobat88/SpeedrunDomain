package com.codebox.speedrun.domain.data.datasource.players.di

import com.codebox.speedrun.domain.data.datasource.players.PlayersRepositoryImpl
import com.codebox.speedrun.domain.data.repo.players.PlayersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface PlayersRepositoryModule {
    @Binds
    fun bindPlayersRepository(impl: PlayersRepositoryImpl): PlayersRepository
}

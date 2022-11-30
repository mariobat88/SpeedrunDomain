package com.speedrun.domain.datasource.di

import com.speedrun.domain.datasource.games.GamesRepositoryImpl
import com.speedrun.domain.data.datasource.leaderboards.LeaderboardsRepositoryImpl
import com.speedrun.domain.datasource.runs.RunsRepositoryImpl
import com.speedrun.domain.datasource.publishers.PublishersRepositoryImpl
import com.speedrun.domain.datasource.players.PlayersRepositoryImpl
import com.speedrun.domain.datasource.categories.CategoriesRepositoryImpl
import com.speedrun.domain.datasource.developers.DevelopersRepositoryImpl
import com.speedrun.domain.repo.categories.CategoriesRepository
import com.speedrun.domain.repo.developers.DevelopersRepository
import com.speedrun.domain.repo.games.GamesRepository
import com.speedrun.domain.repo.leaderboards.LeaderboardsRepository
import com.speedrun.domain.repo.players.PlayersRepository
import com.speedrun.domain.repo.publishers.PublishersRepository
import com.speedrun.domain.repo.runs.RunsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface RepositoriesModule {
    @Binds
    fun bindCategoriesRepository(impl: CategoriesRepositoryImpl): CategoriesRepository

    @Binds
    fun bindDevelopersRepository(impl: DevelopersRepositoryImpl): DevelopersRepository

    @Binds
    fun bindGamesRepository(impl: GamesRepositoryImpl): GamesRepository

    @Binds
    fun bindLeaderboardsRepository(impl: LeaderboardsRepositoryImpl): LeaderboardsRepository

    @Binds
    fun bindPlayersRepository(impl: PlayersRepositoryImpl): PlayersRepository

    @Binds
    fun bindPublishersRepository(impl: PublishersRepositoryImpl): PublishersRepository

    @Binds
    fun bindRunsRepository(impl: RunsRepositoryImpl): RunsRepository
}

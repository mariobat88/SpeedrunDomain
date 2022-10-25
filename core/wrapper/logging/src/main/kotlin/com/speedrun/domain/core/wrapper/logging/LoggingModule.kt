package com.speedrun.domain.core.wrapper.logging

import com.speedrun.domain.core.initializer.AppInitializer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@InstallIn(SingletonComponent::class)
@Module
abstract class LoggingModule {
    @Binds
    @IntoSet
    abstract fun bindTimberInitializer(bind: TimberInitializer): AppInitializer
}

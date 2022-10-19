package com.codebox.speedrun.domain.networking.core.di

import com.codebox.speedrun.domain.networking.core.adapters.RunTimeEnumAdapter
import com.squareup.moshi.JsonAdapter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
abstract class AdaptersModule {
    @Binds
    @IntoSet
    abstract fun bindRealTimeEnumAdapter(adapter: RunTimeEnumAdapter): JsonAdapter<*>
}

package com.speedrun.domain.data.database.di

import android.content.Context
import androidx.room.Room
import com.speedrun.domain.data.database.SpeedrunDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideSpeedrunDatabase(
        @ApplicationContext context: Context
    ): SpeedrunDatabase {
        return Room.databaseBuilder(context, SpeedrunDatabase::class.java, "speedrun-db")
            .fallbackToDestructiveMigration()
            .build()
    }
}

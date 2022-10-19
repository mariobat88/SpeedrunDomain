package com.speedrun.domain.di

import android.app.Application
import com.speedrun.domain.BuildConfig
import com.speedrun.domain.core.annotations.ApiUrl
import com.speedrun.domain.core.annotations.AppVersionName
import com.speedrun.domain.core.annotations.DebugBuild
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @DebugBuild
    fun provideIsDebugBuild(): Boolean = BuildConfig.DEBUG

    @Provides
    @AppVersionName
    fun provideAppVersionName(application: Application): String {
        val packageInfo = application.packageManager.getPackageInfo(application.packageName, 0)
        return packageInfo.versionName
    }

    @Provides
    @ApiUrl
    fun provideApiUrl(): String = BuildConfig.API_URL
}

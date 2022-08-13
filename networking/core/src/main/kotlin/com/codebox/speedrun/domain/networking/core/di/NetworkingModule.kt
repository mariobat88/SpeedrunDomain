package com.codebox.speedrun.domain.networking.core.di

import com.codebox.speedrun.domain.annotations.ApiUrl
import com.codebox.speedrun.domain.annotations.AppVersionName
import com.codebox.speedrun.domain.annotations.DebugBuild
import com.squareup.moshi.Moshi
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkingModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(
        @DebugBuild isDebug: Boolean,
    ): HttpLoggingInterceptor? =
        if (isDebug) HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        else null

    @Singleton
    @Provides
    fun provideOkHttpClient(
        @AppVersionName versionName: String,
        httpLoggingInterceptor: HttpLoggingInterceptor?,
    ): OkHttpClient = OkHttpClient.Builder()
        .apply {
            if (httpLoggingInterceptor != null) {
                addInterceptor(httpLoggingInterceptor)
            }
        }
        .addNetworkInterceptor { chain ->
            val request = chain.request().newBuilder()
                .removeHeader("user-agent")
                .addHeader("user-agent", "SpeedrunDomain/$versionName")
                .build()
            chain.proceed(request)
        }
        .build()

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Singleton
    @Provides
    fun provideMoshiConverterFactory(
        moshi: Moshi,
    ): MoshiConverterFactory = MoshiConverterFactory.create(moshi)

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: Lazy<OkHttpClient>,
        moshiConverterFactory: MoshiConverterFactory,
        @ApiUrl apiUrl: String,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(apiUrl)
        .callFactory { okHttpClient.get().newCall(it) }
        .addConverterFactory(moshiConverterFactory)
        .build()
}
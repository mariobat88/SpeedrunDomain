package com.codebox.speedrun.domain.networking.core.di

import android.app.Application
import com.codebox.speedrun.domain.annotations.ApiUrl
import com.codebox.speedrun.domain.annotations.AppVersionName
import com.codebox.speedrun.domain.annotations.DebugBuild
import com.codebox.speedrun.domain.networking.api.players.PlayerResponse
import com.codebox.speedrun.domain.networking.api.players.PlayerType
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
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
        application: Application,
        httpLoggingInterceptor: HttpLoggingInterceptor?,
    ): OkHttpClient {
        val file = File(application.cacheDir, "http-cache")
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        val cache = Cache(file, cacheSize.toLong())

        val builder = OkHttpClient.Builder()
            .apply {
                if (httpLoggingInterceptor != null) {
                    addInterceptor(httpLoggingInterceptor)
                }
            }
            .cache(cache)
            .addNetworkInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .removeHeader("user-agent")
                    .addHeader("user-agent", "SpeedrunDomain/$versionName")
                    .build()
                chain.proceed(request)
            }

        return builder.build()
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(
            PolymorphicJsonAdapterFactory.of(PlayerResponse::class.java, "rel")
                .withSubtype(PlayerResponse.UserResponse::class.java, PlayerType.user.name)
                .withSubtype(PlayerResponse.GuestResponse::class.java, PlayerType.guest.name)
        )
        .build()

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
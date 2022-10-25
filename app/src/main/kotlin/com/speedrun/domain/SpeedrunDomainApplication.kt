package com.speedrun.domain

import android.app.Application
import com.speedrun.domain.core.initializer.AppInitializer
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class SpeedrunDomainApplication : Application(){

    @Inject
    lateinit var initializers: Set<@JvmSuppressWildcards AppInitializer>

    override fun onCreate() {
        super.onCreate()
        initializers.forEach {
            it.init(this)
        }
    }
}

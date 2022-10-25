package com.speedrun.domain.core.wrapper.logging

import android.app.Application
import com.speedrun.domain.core.annotations.DebugBuild
import com.speedrun.domain.core.initializer.AppInitializer
import timber.log.Timber
import javax.inject.Inject

class TimberInitializer @Inject constructor(
    @DebugBuild private val isDebug: Boolean
) : AppInitializer {
    override fun init(application: Application) {
        if (isDebug) {
            Timber.plant(Timber.DebugTree())
        }
    }
}

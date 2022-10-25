package com.speedrun.domain.core.initializer

import android.app.Application

interface AppInitializer {
    fun init(application: Application)
}

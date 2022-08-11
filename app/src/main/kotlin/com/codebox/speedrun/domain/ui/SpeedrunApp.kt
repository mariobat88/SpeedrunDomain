package com.codebox.speedrun.domain.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.codebox.speedrun.domain.dashboard.NavGraphs
import com.codebox.speedrun.domain.designsystem.theme.LightDefaultColorScheme
import com.ramcosta.composedestinations.DestinationsNavHost

@Composable
fun SpeedrunApp() {
    MaterialTheme(
        colorScheme = LightDefaultColorScheme
    ) {
        DestinationsNavHost(navGraph = NavGraphs.root)
    }
}
package com.codebox.speedrun.domain.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

import com.codebox.speedrun.domain.designsystem.theme.LightDefaultColorScheme
import com.codebox.speedrun.domain.navigation.MainNavigatorImpl
import com.codebox.speedrun.domain.navigation.AppNavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency

@Composable
fun App() {
    MaterialTheme(
        colorScheme = LightDefaultColorScheme
    ) {
        DestinationsNavHost(
            navGraph = AppNavGraphs.root,
            dependenciesContainerBuilder = {
                val mainNavigator = remember { MainNavigatorImpl(destinationsNavigator) }
                remember { AppplicationViewModel(mainNavigator) }
                dependency(mainNavigator)
            },
        )
    }
}

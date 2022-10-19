package com.speedrun.domain.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.speedrun.domain.core.designsystem.theme.LightDefaultColorScheme
import com.speedrun.domain.navigation.AppNavigation

@Composable
fun AppScreen() {
    MaterialTheme(
        colorScheme = LightDefaultColorScheme
    ) {
        val appViewModel = AppViewModel.create()
        val navController = rememberNavController()
        AppNavigation(navController, appViewModel)
    }
}

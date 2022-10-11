package com.codebox.speedrun.domain.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.codebox.speedrun.domain.designsystem.theme.LightDefaultColorScheme

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

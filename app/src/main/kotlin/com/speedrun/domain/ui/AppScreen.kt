package com.speedrun.domain.ui

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.speedrun.domain.core.designsystem.theme.DarkDefaultColorScheme
import com.speedrun.domain.core.designsystem.theme.LightDefaultColorScheme
import com.speedrun.domain.navigation.AppNavigation

@Composable
fun AppScreen() {
    val darkTheme = isSystemInDarkTheme()
    val dynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

    val colorScheme = when {
        dynamicColor && darkTheme -> dynamicDarkColorScheme(LocalContext.current)
        dynamicColor && !darkTheme -> dynamicLightColorScheme(LocalContext.current)
        darkTheme -> DarkDefaultColorScheme
        else -> LightDefaultColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
    ) {
        val appViewModel = AppViewModel.create()
        val navController = rememberNavController()
        AppNavigation(navController, appViewModel)
    }
}

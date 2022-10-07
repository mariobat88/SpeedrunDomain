package com.codebox.speedrun.domain.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.codebox.speedrun.domain.designsystem.theme.LightDefaultColorScheme

@Composable
fun App() {
    MaterialTheme(
        colorScheme = LightDefaultColorScheme
    ) {
        AppNavigation()
    }
}

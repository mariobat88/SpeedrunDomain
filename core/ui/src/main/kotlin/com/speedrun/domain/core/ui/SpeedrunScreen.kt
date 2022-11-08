package com.speedrun.domain.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SpeedrunScreen(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    statusBarColor: Color = Color.Transparent,
    statusBarDarkIcons: Boolean? = null,
    navigationBarColor:Color =  Color.Transparent,
    navigationBarDarkIcons: Boolean? = null,
    navigationBarContrastEnforced: Boolean = false,
    content: @Composable BoxScope.(paddingValue: PaddingValues) -> Unit,
) {
    val darkTheme = isSystemInDarkTheme()
    val systemUiController = rememberSystemUiController()

    systemUiController.setStatusBarColor(
        color = statusBarColor,
        darkIcons = statusBarDarkIcons ?: !darkTheme,
    )

    systemUiController.setNavigationBarColor(
        color = navigationBarColor,
        darkIcons = navigationBarDarkIcons?: !darkTheme,
        navigationBarContrastEnforced = navigationBarContrastEnforced
    )

    val statusBarBottomPadding =
        WindowInsets.Companion.statusBars.asPaddingValues().calculateTopPadding()
    val navigationBottomPadding =
        WindowInsets.Companion.navigationBars.asPaddingValues().calculateBottomPadding()
    val paddingValues =
        PaddingValues(top = statusBarBottomPadding, bottom = navigationBottomPadding)
    Box(
        modifier = modifier.background(backgroundColor)
    ) {
        content(paddingValues)
    }
}

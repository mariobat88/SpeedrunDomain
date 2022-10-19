package com.speedrun.domain.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun SpeedrunScreen(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    content: @Composable BoxScope.(paddingValue: PaddingValues) -> Unit
) {
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

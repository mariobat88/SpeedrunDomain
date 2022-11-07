package com.speedrun.domain.core.designsystem.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val LightDefaultColorScheme = lightColorScheme(
    primary = SpeedrunColors.Green,
    onPrimary = Color.White,
    background = SpeedrunColors.BrightGray,
    onBackground = Color.Black,
    secondary = SpeedrunColors.Platinum,
    onSecondary = Color.Black,
    tertiary = Color.White,
    onTertiary = Color.Black,
)

val DarkDefaultColorScheme = darkColorScheme(
    primary = SpeedrunColors.Green,
    onPrimary = Color.White,
    background = SpeedrunColors.ChineseBlack,
    onBackground = Color.White,
    secondary = SpeedrunColors.Gunmetal,
    onSecondary = Color.White,
    tertiary = SpeedrunColors.DarkGunmetal,
    onTertiary = Color.White,
)

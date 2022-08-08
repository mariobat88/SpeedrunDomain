package com.codebox.speedrun.domain.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp
import com.codebox.speedrun.domain.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SpeedrunApp() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.navbardark))
    ){

        val systemUiController = rememberSystemUiController()

        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            //darkIcons = statusBarDarkIcons,
        )

        systemUiController.setNavigationBarColor(
            color = Color.Transparent,
            //darkIcons = navigationBarDarkIcons,
            navigationBarContrastEnforced = false,
        )

       Text(
           text = "BATBAT TEST",
           modifier = Modifier.wrapContentSize(),
           color = Color.Black,
           fontSize = 30.sp
       )
    }
}
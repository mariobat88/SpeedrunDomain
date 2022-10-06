package com.codebox.speedrun.domain.dashboard.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.codebox.speedrun.domain.dashboard.DashboardNavGraphs
import com.ramcosta.composedestinations.annotation.Destination

const val HOME_SCREEN_ROUTE = "HomeScreen"

@Destination(HOME_SCREEN_ROUTE)
@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    )
}

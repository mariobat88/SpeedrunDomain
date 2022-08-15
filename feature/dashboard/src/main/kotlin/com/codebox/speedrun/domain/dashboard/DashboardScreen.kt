package com.codebox.speedrun.domain.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(start = true)
@Destination
@Composable
fun DashboardScreen() {
    val viewModel = hiltViewModel<DashboardViewModel>()
    val viewState = viewModel.viewState.collectAsStateWithLifecycle().value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
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

        LazyColumn(
            state = rememberLazyListState()
        ) {
            items(viewState.latestRuns) { run ->
                Column {
                    Text(
                        text = run.game.names.international,
                        modifier = Modifier.wrapContentSize(),
                        color = Color.Black,
                        fontSize = 30.sp
                    )
                }
            }
        }
    }
}
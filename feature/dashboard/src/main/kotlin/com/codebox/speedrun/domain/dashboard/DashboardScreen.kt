package com.codebox.speedrun.domain.dashboard

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
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
            item {
                Column {
                    Divider(
                        color = MaterialTheme.colorScheme.primary,
                        thickness = 10.dp
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                    ) {
                        Text(
                            text = "Latest Runs",
                            modifier = Modifier.padding(start = 16.dp),
                            color = Color.White,
                        )
                    }
                }
            }
            items(viewState.latestRuns) { run ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(horizontal = 16.dp)
                ) {
                    AsyncImage(
                        model = run.game.assets.coverMedium.uri,
                        contentDescription = "",
                        modifier = Modifier.width(60.dp),
                        contentScale = ContentScale.FillWidth
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = run.game.names.international,
                        modifier = Modifier.fillMaxWidth()
                            .wrapContentHeight(),
                        color = Color.White,
                        fontSize = 12.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}
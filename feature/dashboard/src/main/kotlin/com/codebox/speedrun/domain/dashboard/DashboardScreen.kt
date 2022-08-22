package com.codebox.speedrun.domain.dashboard

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
import androidx.compose.ui.res.dimensionResource
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
            state = rememberLazyListState(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
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
            items(viewState.latestRuns) { latestRun ->
                Column(
                    modifier = Modifier.padding(horizontal = dimensionResource(com.codebox.speedrun.domain.core.designsystem.R.dimen.side_padding))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        AsyncImage(
                            model = latestRun.game.assets.coverMedium.uri,
                            contentDescription = "",
                            modifier = Modifier.width(60.dp),
                            contentScale = ContentScale.FillWidth
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = latestRun.game.names.international,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(),
                                color = Color.White,
                                fontSize = 16.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            latestRun.runs.forEach { run ->
                                Row(
                                    modifier = Modifier.fillMaxWidth()
                                        .padding(vertical = 4.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = run.category.name,
                                        modifier = Modifier.wrapContentSize(),
                                        color = Color.White,
                                        fontSize = 12.sp,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Column(
                                        modifier = Modifier.wrapContentWidth(),
                                        horizontalAlignment = Alignment.Start
                                    ) {
                                        Text(
                                            text = run.players.getOrNull(0)?.name
                                                ?: run.players.getOrNull(0)?.id ?: "",
                                            modifier = Modifier.wrapContentSize(),
                                            color = Color.White,
                                            fontSize = 10.sp,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                        Text(
                                            text = run.times.primaryT.toString(),
                                            modifier = Modifier.wrapContentSize(),
                                            color = Color.White,
                                            fontSize = 10.sp,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Divider()
                }
            }
        }
    }
}
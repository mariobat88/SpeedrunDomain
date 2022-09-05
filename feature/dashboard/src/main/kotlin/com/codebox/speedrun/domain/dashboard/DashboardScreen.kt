package com.codebox.speedrun.domain.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.codebox.speedrun.domain.core.framework.toElapsedTime
import com.codebox.speedrun.domain.kit.player.ui.PlayerName
import com.codebox.speedrun.domain.ui.SpeedrunScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.dependency
import kotlin.math.max

@RootNavGraph(start = true)
@Destination
@Composable
fun DashboardScreen() {
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

    val dashboardNavController = rememberNavController()

    SpeedrunScreen { screenPadding ->
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(top = screenPadding.calculateTopPadding()),
            bottomBar = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(MaterialTheme.colorScheme.primary),
                ) {
                    DashboardTabs.values().forEach { tab ->
                        Column(
                            modifier = Modifier
                                .wrapContentHeight()
                                .weight(1f)
                                .clickable {
                                    dashboardNavController.navigate(
                                        route = tab.route, navOptions = NavOptions
                                            .Builder()
                                            .setPopUpTo(
                                                route = dashboardNavController.currentDestination?.route,
                                                inclusive = true,
                                                saveState = true
                                            )
                                            .setRestoreState(true)
                                            .build()
                                    )
                                }
                        ) {
                            Box( modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp)
                            ) {
                                Text(
                                    text = stringResource(tab.titleRes),
                                    modifier = Modifier.align(Alignment.Center),
                                    color = MaterialTheme.colorScheme.onPrimary,
                                )
                            }

                            Spacer(modifier = Modifier.padding(bottom = screenPadding.calculateBottomPadding()))
                        }
                    }
                }
            }
        ) { paddingValues ->
            DestinationsNavHost(
                navGraph = NavGraphs.dashboard,
                navController = dashboardNavController,
                dependenciesContainerBuilder = {
                    dependency(paddingValues)
                }
            )
        }
    }
}

@DashboardNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
) {
    val viewModel = hiltViewModel<DashboardViewModel>()
    val viewState = viewModel.viewState.collectAsStateWithLifecycle().value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(paddingValues)
    ) {
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
                            val density = LocalDensity.current
                            val maxNameWidth = remember { mutableStateOf<Int?>(null) }

                            latestRun.runs.forEach { run ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
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
                                        modifier = Modifier.wrapContentHeight()
                                            .run {
                                                if (maxNameWidth.value != null) {
                                                    val maxNameWidthDp =
                                                        with(density) { maxNameWidth.value!!.toDp() }
                                                    Modifier.width(maxNameWidthDp)
                                                } else {
                                                    Modifier.wrapContentWidth()
                                                }
                                            },
                                        horizontalAlignment = Alignment.Start
                                    ) {
                                        PlayerName(
                                            modifier = Modifier
                                                .wrapContentSize()
                                                .onGloballyPositioned { layoutCoordinates ->
                                                    maxNameWidth.value = max(
                                                        maxNameWidth.value ?: 0,
                                                        layoutCoordinates.size.width
                                                    )
                                                },
                                            player = run.players[0],
                                        )
                                        Text(
                                            text = toElapsedTime(run.times.primary),
                                            modifier = Modifier
                                                .wrapContentSize()
                                                .onGloballyPositioned { layoutCoordinates ->
                                                    maxNameWidth.value = max(
                                                        maxNameWidth.value ?: 0,
                                                        layoutCoordinates.size.width
                                                    )
                                                },
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

@DashboardNavGraph
@Destination
@Composable
fun LatestRunsScreen(
    paddingValues: PaddingValues,
) {
    val viewModel = hiltViewModel<DashboardViewModel>()
    val viewState = viewModel.viewState.collectAsStateWithLifecycle().value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onBackground)
            .padding(paddingValues)
    ) {
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
                            val density = LocalDensity.current
                            val maxNameWidth = remember { mutableStateOf<Int?>(null) }

                            latestRun.runs.forEach { run ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
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
                                        modifier = Modifier.wrapContentHeight()
                                            .run {
                                                if (maxNameWidth.value != null) {
                                                    val maxNameWidthDp =
                                                        with(density) { maxNameWidth.value!!.toDp() }
                                                    Modifier.width(maxNameWidthDp)
                                                } else {
                                                    Modifier.wrapContentWidth()
                                                }
                                            },
                                        horizontalAlignment = Alignment.Start
                                    ) {
                                        PlayerName(
                                            modifier = Modifier
                                                .wrapContentSize()
                                                .onGloballyPositioned { layoutCoordinates ->
                                                    maxNameWidth.value = max(
                                                        maxNameWidth.value ?: 0,
                                                        layoutCoordinates.size.width
                                                    )
                                                },
                                            player = run.players[0],
                                        )
                                        Text(
                                            text = toElapsedTime(run.times.primary),
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

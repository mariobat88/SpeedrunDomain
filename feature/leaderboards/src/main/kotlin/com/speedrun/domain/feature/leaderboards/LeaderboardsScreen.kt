@file:OptIn(ExperimentalPagerApi::class)

package com.speedrun.domain.feature.leaderboards

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.speedrun.domain.core.framework.Screen
import com.speedrun.domain.core.framework.async.Loading
import com.speedrun.domain.core.framework.async.Success
import com.speedrun.domain.core.ui.SpeedrunScreen
import com.speedrun.domain.kit.player.ui.PlayerName
import com.speedrun.domain.core.designsystem.R as DesignSystemResources
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration

@Composable
internal fun LeaderboardsScreen(
    gameId: String
) {
    val leaderboardsViewModel = LeaderboardsViewModel.create(gameId)
    LeaderboardsScreen(leaderboardsViewModel)
}


@Composable
fun LeaderboardsScreen(
    viewModel: LeaderboardsViewModel
) = Screen(viewModel) { viewState, intentChannel, _ ->
    LeaderboardsScreen(viewState, intentChannel)
}

@Composable
fun LeaderboardsScreen(
    viewState: ViewState,
    intentChannel: MutableSharedFlow<Intent>,
) {
    SpeedrunScreen(
        modifier = Modifier.fillMaxSize()
    ) { screenPadding ->
        Column(
            modifier = Modifier.padding(screenPadding)
        ) {
            viewState.categoriesAsync()?.let {
                val pagerState = rememberPagerState()
                val coroutineScope = rememberCoroutineScope()

                LaunchedEffect(pagerState) {
                    // Collect from the pager state a snapshotFlow reading the currentPage
                    snapshotFlow { pagerState.currentPage }.collect { page ->
                        intentChannel.tryEmit(Intent.CategorySelected(page))
                    }
                }

                androidx.compose.material.ScrollableTabRow(
                    selectedTabIndex = pagerState.currentPage,
                    modifier = Modifier.fillMaxWidth(),
                    edgePadding = 0.dp,
                    backgroundColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    indicator = { tabPositions ->
                        TabRowDefaults.Indicator(
                            Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                        )
                    }
                ) {
                    viewState.categoriesAsync()?.forEachIndexed { index, category ->
                        Tab(
                            selected = pagerState.currentPage == index,
                            onClick = { coroutineScope.launch { pagerState.scrollToPage(index) } },
                            text = {
                                Text(
                                    text = category.name,
                                    color = MaterialTheme.colorScheme.onPrimary,
                                )
                            }
                        )
                    }
                }
                HorizontalPager(
                    count = viewState.categoriesAsync()?.size ?: 0,
                    state = pagerState,
                ) { page ->
                    when (val leaderboardAsync = viewState.leaderboardsMap[page]) {
                        is Loading -> {
                        }
                        is Success -> {
                            LazyColumn {
                                leaderboardAsync().runs.forEach { run ->
                                    item(run.run?.id) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(40.dp)
                                                .padding(horizontal = dimensionResource(DesignSystemResources.dimen.side_padding)),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceAround
                                        ) {
                                            Box(
                                                modifier = Modifier.size(20.dp),
                                                contentAlignment = Alignment.Center
                                            ){
                                                val imageUri = when (run.place) {
                                                    1 -> run.run?.game?.assets?.trophy1st
                                                    2 -> run.run?.game?.assets?.trophy2nd
                                                    3 -> run.run?.game?.assets?.trophy3rd
                                                    4 -> run.run?.game?.assets?.trophy4th
                                                    else -> null
                                                }
                                                if (imageUri != null) {
                                                    AsyncImage(
                                                        model = imageUri,
                                                        contentDescription = "",
                                                        modifier = Modifier.fillMaxSize()
                                                    )
                                                } else {
                                                    Text(
                                                        text = run.place.toString(),
                                                        color = MaterialTheme.colorScheme.onPrimary
                                                    )
                                                }
                                            }
                                            Column {
                                                run.run?.players?.forEach {
                                                    PlayerName(player = it)
                                                }
                                            }
                                            Text(
                                                text = Duration.parseIsoString(run.run?.times?.primary ?: "").toString(),
                                                color = MaterialTheme.colorScheme.onPrimary
                                            )
                                            Text(
                                                text = run.run?.date ?: "",
                                                color = MaterialTheme.colorScheme.onPrimary
                                            )
                                            Text(
                                                text = run.run?.system?.platform?.name ?: "",
                                                color = MaterialTheme.colorScheme.onPrimary
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        else -> {
                        }
                    }
                }
            }
        }

    }
}

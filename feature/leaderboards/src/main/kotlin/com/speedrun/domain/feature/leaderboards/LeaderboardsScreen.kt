@file:OptIn(ExperimentalPagerApi::class)

package com.speedrun.domain.feature.leaderboards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.speedrun.domain.core.framework.Screen
import com.speedrun.domain.core.framework.async.Loading
import com.speedrun.domain.core.framework.async.Success
import com.speedrun.domain.core.ui.SpeedrunScreen
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

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
            val pagerState = rememberPagerState()
            val coroutineScope = rememberCoroutineScope()

            LaunchedEffect(pagerState) {
                // Collect from the pager state a snapshotFlow reading the currentPage
                snapshotFlow { pagerState.currentPage }.collect { page ->
                    intentChannel.tryEmit(Intent.CategorySelected(page))
                }
            }


            viewState.categoriesAsync()?.let {
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
                                    color =  MaterialTheme.colorScheme.onPrimary,
                                )
                            }
                        )
                    }
                }
                HorizontalPager(
                    count = viewState.categoriesAsync()?.size ?: 0,
                    state = pagerState,
                ) { page ->
//                    val leaderboardAsync = viewState.leaderboardsMap[page]
//                    when(val leaderboard = leaderboardAsync){
//                        is Loading ->{
//
//                        }
//                        is Success -> {
//                            val runs = leaderboard().runs
//                            LazyColumn{
//                                leaderboard().runs.forEach {  run ->
//                                    key(run.run) {
//                                        Column {
//                                            Text(text = run.run)
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                         else -> {
//
//                         }
//                    }
                }
            }
        }

    }
}

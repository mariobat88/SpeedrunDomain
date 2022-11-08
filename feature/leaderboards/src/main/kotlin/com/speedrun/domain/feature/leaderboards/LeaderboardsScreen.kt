@file:OptIn(ExperimentalPagerApi::class)

package com.speedrun.domain.feature.leaderboards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.speedrun.domain.core.framework.Screen
import com.speedrun.domain.core.framework.async.Loading
import com.speedrun.domain.core.framework.async.Success
import com.speedrun.domain.core.framework.countryFlag
import com.speedrun.domain.core.ui.SpeedrunScreen
import com.speedrun.domain.data.repo.players.model.PlayerModel
import com.speedrun.domain.kit.player.ui.PlayerName
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration
import com.speedrun.domain.core.designsystem.R as DesignSystemResources

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
    val superscript = SpanStyle(
        baselineShift = BaselineShift.Superscript,
        fontSize = 10.sp,
        color = MaterialTheme.colorScheme.onBackground
    )

    SpeedrunScreen(
        modifier = Modifier.fillMaxSize()
    ) { screenPadding ->
        Column(
            modifier = Modifier.padding(top = screenPadding.calculateTopPadding())
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
                                    color = MaterialTheme.colorScheme.onBackground,
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
                            LazyColumn(
                                modifier = Modifier.fillMaxSize(),
                                contentPadding = PaddingValues(bottom = screenPadding.calculateBottomPadding())
                            ) {
                                leaderboardAsync().runs.forEachIndexed { index, run ->
                                    item(run.run?.id) {
                                        val background = if (index % 2 == 0) {
                                            MaterialTheme.colorScheme.secondary
                                        } else {
                                            MaterialTheme.colorScheme.tertiary
                                        }
                                        val textColor = if (index % 2 == 0) {
                                            MaterialTheme.colorScheme.onSecondary
                                        } else {
                                            MaterialTheme.colorScheme.onTertiary
                                        }

                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(40.dp)
                                                .background(background)
                                                .padding(
                                                    horizontal = dimensionResource(
                                                        DesignSystemResources.dimen.side_padding
                                                    )
                                                ),
                                            verticalAlignment = Alignment.CenterVertically,
                                        ) {
                                            Row(
                                                modifier = Modifier
                                                    .weight(1f)
                                                    .fillMaxHeight(),
                                                horizontalArrangement = Arrangement.Start,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Box(
                                                    modifier = Modifier.size(20.dp),
                                                    contentAlignment = Alignment.CenterStart
                                                ) {
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
                                                            color = textColor
                                                        )
                                                    }
                                                }
                                                Column(
                                                    modifier = Modifier.wrapContentSize(),
                                                ) {
                                                    run.run?.players?.forEach { player ->
                                                        Row {
                                                            if (player is PlayerModel.UserModel) {
                                                                Text(
                                                                    text = countryFlag(
                                                                        player.location?.country?.code
                                                                            ?: ""
                                                                    ),
                                                                    modifier = Modifier.wrapContentSize()
                                                                )
                                                            }
                                                            Spacer(modifier = Modifier.width(2.dp))
                                                            PlayerName(player = player)
                                                        }

                                                    }
                                                }
                                            }
                                            Box(
                                                modifier = Modifier
                                                    .weight(1f)
                                                    .fillMaxHeight(),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    text = Duration.parseIsoString(
                                                        run.run?.times?.primary ?: ""
                                                    ).toString(),

                                                    color = textColor,
                                                    textAlign = TextAlign.Center
                                                )
                                            }
                                            Box(
                                                modifier = Modifier
                                                    .weight(1f)
                                                    .fillMaxHeight(),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    text = buildAnnotatedString {
                                                        append(
                                                            run.run?.system?.platform?.name ?: ""
                                                        )
                                                        if (run.run?.system?.emulated == true) {
                                                            withStyle(superscript) {
                                                                append(" EMU")
                                                            }
                                                        }

                                                    },
                                                    color = textColor,
                                                    textAlign = TextAlign.Center
                                                )
                                            }
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

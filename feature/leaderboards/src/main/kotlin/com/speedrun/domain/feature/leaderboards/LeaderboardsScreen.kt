@file:OptIn(ExperimentalPagerApi::class)

package com.speedrun.domain.feature.leaderboards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
import com.speedrun.domain.data.repo.players.model.PlayerModel
import com.speedrun.domain.feature.leaderboards.navigation.LeaderboardNavigator
import com.speedrun.domain.kit.player.ui.CountryFlag
import com.speedrun.domain.kit.player.ui.PlayerName
import com.speedrun.domain.kit.run.ui.System
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration
import com.speedrun.domain.core.designsystem.R as DesignSystemResources
import com.speedrun.domain.feature.leaderboards.R as LeaderboardsResources

@Composable
internal fun LeaderboardsScreen(
    leaderboardNavigator: LeaderboardNavigator
) {
    val leaderboardsViewModel = LeaderboardsViewModel.create(leaderboardNavigator)
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
            modifier = Modifier.padding(top = screenPadding.calculateTopPadding())
        ) {
            viewState.categoriesAsync()?.let {
                val pagerState = rememberPagerState()
                val coroutineScope = rememberCoroutineScope()

                if (viewState.categoriesAsync()?.isNotEmpty() == true) {
                    LaunchedEffect(pagerState) {
                        // Collect from the pager state a snapshotFlow reading the currentPage
                        snapshotFlow { pagerState.currentPage }.collect { page ->
                            intentChannel.tryEmit(Intent.CategorySelected(page))
                        }
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
                            Column {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(60.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Button(
                                        onClick = { /*TODO*/ }
                                    ) {
                                        Icon(
                                            painter = painterResource(LeaderboardsResources.drawable.ic_baseline_filter_list_24),
                                            contentDescription = "",
                                            tint = MaterialTheme.colorScheme.onBackground
                                        )
                                        Spacer(
                                            modifier = Modifier.width(dimensionResource(DesignSystemResources.dimen.small_spacing)))
                                                    Text (
                                                    text =
                                                        stringResource(LeaderboardsResources.string.filters),
                                            color = MaterialTheme.colorScheme.onBackground,
                                        )
                                    }
                                }

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
                                                    )
                                                    .clickable {
                                                        intentChannel.tryEmit(
                                                            Intent.RunClicked(
                                                                run.run?.id
                                                            )
                                                        )
                                                    },
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
                                                        contentAlignment = Alignment.Center
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
                                                    Spacer(modifier = Modifier.width(4.dp))
                                                    Column(
                                                        modifier = Modifier.wrapContentSize(),
                                                    ) {
                                                        run.run?.players?.forEach { player ->
                                                            Row(
                                                                modifier = Modifier.wrapContentSize(),
                                                                verticalAlignment = Alignment.CenterVertically
                                                            ) {
                                                                if (player is PlayerModel.UserModel) {
                                                                    CountryFlag(
                                                                        modifier = Modifier.wrapContentSize(),
                                                                        countryCode = player.location?.country?.code
                                                                    )
                                                                }
                                                                Spacer(modifier = Modifier.width(2.dp))
                                                                PlayerName(
                                                                    modifier = Modifier.wrapContentSize(),
                                                                    player = player,
                                                                )
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
                                                    System(
                                                        system = run.run?.system,
                                                        color = textColor,
                                                        textAlign = TextAlign.Center
                                                    )
                                                }
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

package com.codebox.speedrun.domain.dashboard.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.codebox.speedrun.domain.core.framework.Compose
import com.codebox.speedrun.domain.core.framework.countryFlag
import com.codebox.speedrun.domain.kit.player.ui.PlayerName
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.placeholder.placeholder
import com.codebox.speedrun.domain.core.designsystem.R as DesignSystemResources
import com.codebox.speedrun.domain.core.ui.R as UIResources
import com.codebox.speedrun.domain.dashboard.R as DashboardResources

@Composable
fun SearchScreen(
    searchNavigator: SearchNavigator,
) {
    val searchViewModel = SearchViewModel.create(searchNavigator)
    SearchScreen(searchViewModel)
}

@Composable
private fun SearchScreen(
    viewModel: SearchViewModel,
) = Compose(viewModel) { viewState, intentChannel, _ ->
    val sidePadding = dimensionResource(DesignSystemResources.dimen.side_padding)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = sidePadding)
    ) {
        val searchedGames =
            viewModel.searchGames.collectAsStateWithLifecycle().value.collectAsLazyPagingItems()
        val searchPlayers =
            viewModel.searchPlayers.collectAsStateWithLifecycle().value.collectAsLazyPagingItems()

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(MaterialTheme.colorScheme.background),
            ) {
                TabRow(
                    selectedTabIndex = viewState.selectedTab.ordinal,
                    modifier = Modifier
                        .fillMaxWidth(),
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ) {
                    Tab(
                        selected = viewState.selectedTab == ViewState.TAB.GAMES,
                        onClick = { intentChannel.tryEmit(Intent.TabSelected(0)) },
                        text = {
                            Text(text = stringResource(DashboardResources.string.games))
                        }
                    )
                    Tab(
                        selected = viewState.selectedTab == ViewState.TAB.PLAYERS,
                        onClick = { intentChannel.tryEmit(Intent.TabSelected(1)) },
                        text = {
                            Text(text = stringResource(DashboardResources.string.users))
                        }
                    )
                }
                Spacer(modifier = Modifier.height(sidePadding))
                TextField(
                    value = viewModel.searchTerm,
                    onValueChange = { intentChannel.tryEmit(Intent.Search(it)) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent
                    )
                )
                Spacer(modifier = Modifier.height(sidePadding))
            }
            if (viewState.selectedTab == ViewState.TAB.GAMES) {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(dimensionResource(DashboardResources.dimen.item_width)),
                    contentPadding = PaddingValues(
                        bottom = sidePadding
                    ),
                    verticalArrangement = Arrangement.spacedBy(sidePadding / 2),
                    horizontalArrangement = Arrangement.spacedBy(sidePadding / 2)
                ) {

                    if (searchedGames.loadState.refresh == LoadState.Loading) {
                        items(SearchViewModel.INITIAL_LOAD_SIZE) {
                            Spacer(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(dimensionResource(DashboardResources.dimen.rounded_corner_size)))
                                    .border(
                                        0.5.dp,
                                        Color.DarkGray,
                                        RoundedCornerShape(dimensionResource(DashboardResources.dimen.rounded_corner_size))
                                    )
                                    .height(dimensionResource(DashboardResources.dimen.item_height))
                                    .placeholder(
                                        visible = searchedGames.loadState.refresh == LoadState.Loading,
                                        color = MaterialTheme.colorScheme.background,
                                        highlight = PlaceholderHighlight.shimmer()
                                    ),
                            )
                        }
                    } else {
                        items(searchedGames.itemCount) { index ->
                            key(index) {
                                val gameModel = searchedGames[index]
                                Column(
                                    modifier = Modifier
                                        .clip(
                                            RoundedCornerShape(
                                                dimensionResource(
                                                    DashboardResources.dimen.rounded_corner_size
                                                )
                                            )
                                        )
                                        .border(
                                            0.5.dp,
                                            Color.DarkGray,
                                            RoundedCornerShape(dimensionResource(DashboardResources.dimen.rounded_corner_size))
                                        )
                                        .height(dimensionResource(DashboardResources.dimen.item_height))
                                        .clickable { intentChannel.tryEmit(Intent.NavigateToGameScreen) },
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    AsyncImage(
                                        model = gameModel?.assets?.coverMedium?.uri,
                                        contentDescription = "",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(dimensionResource(DashboardResources.dimen.image_height)),
                                        contentScale = ContentScale.Crop
                                    )
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(sidePadding / 4),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = gameModel?.names?.international ?: "",
                                            color = MaterialTheme.colorScheme.onPrimary,
                                            fontSize = 12.sp,
                                            textAlign = TextAlign.Center,
                                            maxLines = 2
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(
                        bottom = sidePadding
                    ),
                ) {
                    items(searchPlayers) { player ->
                        key(player!!.id) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp),
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(50.dp)
                                        .align(Alignment.Center),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                ) {
                                    player.assets.image?.uri?.let { uri ->
                                        AsyncImage(
                                            model = uri,
                                            contentDescription = "",
                                            modifier = Modifier.size(50.dp),
                                            contentScale = ContentScale.Inside
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                    } ?: run {
                                        Icon(
                                            imageVector = Icons.Default.Person,
                                            contentDescription = "",
                                            modifier = Modifier.size(50.dp)
                                        )
                                    }
                                    Column(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .wrapContentWidth(),
                                        verticalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .wrapContentHeight(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically,
                                        ) {
                                            PlayerName(
                                                modifier = Modifier.wrapContentSize(),
                                                player = player
                                            )
                                            Row(
                                                modifier = Modifier.wrapContentSize()
                                            ) {
                                                if (player.twitch != null) {
                                                    Image(
                                                        painter = painterResource(UIResources.drawable.icon_twitch_20),
                                                        contentDescription = "",
                                                        modifier = Modifier
                                                            .wrapContentHeight()
                                                            .width(25.dp),
                                                        contentScale = ContentScale.Inside
                                                    )
                                                }
                                                if (player.twitter != null) {
                                                    Image(
                                                        painter = painterResource(UIResources.drawable.icon_twitter_20),
                                                        contentDescription = "",
                                                        modifier = Modifier
                                                            .wrapContentHeight()
                                                            .width(25.dp),
                                                        contentScale = ContentScale.Inside
                                                    )
                                                }
                                                if (player.youtube != null) {
                                                    Image(
                                                        painter = painterResource(UIResources.drawable.icon_youtube_20),
                                                        contentDescription = "",
                                                        modifier = Modifier
                                                            .wrapContentHeight()
                                                            .width(25.dp),
                                                        contentScale = ContentScale.Inside
                                                    )
                                                }
                                            }
                                        }
                                        Row(
                                            modifier = Modifier.wrapContentSize()
                                        ) {
                                            Text(
                                                text = countryFlag(
                                                    player.location?.country?.code ?: ""
                                                ),
                                                modifier = Modifier.wrapContentSize()
                                            )
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text(
                                                text = player.location?.country?.names?.international
                                                    ?: "",
                                                color = MaterialTheme.colorScheme.onPrimary
                                            )
                                        }
                                    }
                                }
                                Divider(
                                    modifier = Modifier.align(Alignment.BottomCenter),
                                    color = Color.DarkGray,
                                    thickness = 0.5.dp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


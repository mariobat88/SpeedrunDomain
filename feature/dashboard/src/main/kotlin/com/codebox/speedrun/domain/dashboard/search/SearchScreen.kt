package com.codebox.speedrun.domain.dashboard.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.codebox.speedrun.domain.core.framework.Compose
import com.codebox.speedrun.domain.dashboard.DashboardNavGraph
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.placeholder.placeholder
import com.ramcosta.composedestinations.annotation.Destination
import kotlin.math.roundToInt
import com.codebox.speedrun.domain.core.designsystem.R as DesignSystemResources
import com.codebox.speedrun.domain.dashboard.R as DashboardResources

@DashboardNavGraph
@Destination
@Composable
fun SearchScreen(
    screenPadding: PaddingValues,
    bottomBarHeight: Dp,
) {
    SearchScreen(
        hiltViewModel(), screenPadding, bottomBarHeight
    )
}

@Composable
private fun SearchScreen(
    viewModel: SearchViewModel,
    screenPadding: PaddingValues,
    bottomBarHeight: Dp,
) = Compose(viewModel) { viewState, intentChannel, _ ->
    val sidePadding = dimensionResource(DesignSystemResources.dimen.side_padding)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = screenPadding.calculateTopPadding())
            .padding(horizontal = sidePadding)
    ) {
        val density = LocalDensity.current
        val toolbarHeightPx = remember { mutableStateOf(0) }
        val toolbarHeightDp = with(density) { toolbarHeightPx.value.toDp() }
        val maxToolbarHeight = remember {
            derivedStateOf {
                with(density) {
                    toolbarHeightPx.value.toFloat() + screenPadding.calculateTopPadding()
                        .roundToPx().toFloat()
                }
            }
        }
        // Our offset to collapse toolbar
        val toolbarOffsetHeightPx = remember { mutableStateOf(0f) }

        val nestedScrollConnection = remember {
            object : NestedScrollConnection {
                override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                    val delta = available.y
                    val newOffset = toolbarOffsetHeightPx.value + delta
                    toolbarOffsetHeightPx.value = newOffset.coerceIn(-maxToolbarHeight.value, 0f)
                    return Offset.Zero
                }
            }
        }

        val searchedItems =
            viewModel.search.collectAsStateWithLifecycle().value.collectAsLazyPagingItems()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(nestedScrollConnection)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(dimensionResource(DashboardResources.dimen.item_width)),
                contentPadding = PaddingValues(
                    top = toolbarHeightDp,
                    bottom = screenPadding.calculateBottomPadding() + bottomBarHeight + sidePadding
                ),
                verticalArrangement = Arrangement.spacedBy(sidePadding / 2),
                horizontalArrangement = Arrangement.spacedBy(sidePadding / 2)
            ) {
                if (searchedItems.loadState.refresh == LoadState.Loading) {
                    items(40) {
                        Column(
                            modifier = Modifier
                                .clip(RoundedCornerShape(dimensionResource(DashboardResources.dimen.rounded_corner_size)))
                                .border(
                                    0.5.dp,
                                    Color.DarkGray,
                                    RoundedCornerShape(dimensionResource(DashboardResources.dimen.rounded_corner_size))
                                )
                                .height(dimensionResource(DashboardResources.dimen.item_height))
                                .placeholder(
                                    visible = searchedItems.loadState.refresh == LoadState.Loading,
                                    color = MaterialTheme.colorScheme.background,
                                    highlight = PlaceholderHighlight.shimmer()
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ){}
                    }
                } else {
                    items(searchedItems.itemCount) { index ->
                        val gameModel = searchedItems[index]
                        Column(
                            modifier = Modifier
                                .clip(RoundedCornerShape(dimensionResource(DashboardResources.dimen.rounded_corner_size)))
                                .border(
                                    0.5.dp,
                                    Color.DarkGray,
                                    RoundedCornerShape(dimensionResource(DashboardResources.dimen.rounded_corner_size))
                                )
                                .height(dimensionResource(DashboardResources.dimen.item_height)),
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .onGloballyPositioned { coordinates ->
                        toolbarHeightPx.value = coordinates.size.height
                    }
                    .offset { IntOffset(x = 0, y = toolbarOffsetHeightPx.value.roundToInt()) }
                    .background(MaterialTheme.colorScheme.background),
            ) {
                TabRow(
                    selectedTabIndex = viewState.selectedTabIndex,
                    modifier = Modifier
                        .fillMaxWidth(),
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ) {
                    Tab(
                        selected = viewState.selectedTabIndex == 0,
                        onClick = { intentChannel.tryEmit(Intent.TabSelected(0)) },
                        text = {
                            Text(text = stringResource(DashboardResources.string.games))
                        }
                    )
                    Tab(
                        selected = viewState.selectedTabIndex == 1,
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
        }
    }
}


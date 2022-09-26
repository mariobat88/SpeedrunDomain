package com.codebox.speedrun.domain.dashboard.search

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.codebox.speedrun.domain.core.framework.Compose
import com.codebox.speedrun.domain.dashboard.DashboardNavGraph
import com.ramcosta.composedestinations.annotation.Destination
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

        val search = viewModel.search.collectAsStateWithLifecycle().value

        TextField(
            value = viewModel.searchTerm,
            onValueChange = { intentChannel.tryEmit(Intent.Search(it)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent
            )
        )

        LazyVerticalGrid(
            columns = GridCells.Adaptive(dimensionResource(DashboardResources.dimen.item_width)),
            contentPadding = PaddingValues(
                top = sidePadding,
                bottom = screenPadding.calculateBottomPadding() + bottomBarHeight + sidePadding
            ),
            verticalArrangement = Arrangement.spacedBy(sidePadding / 2),
            horizontalArrangement = Arrangement.spacedBy(sidePadding / 2)
        ) {
            items(search) { gameModel ->
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
                        model = gameModel.assets.coverMedium.uri,
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
                            text = gameModel.names.international,
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
package com.speedrun.domain.kit.run.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import coil.compose.AsyncImage
import com.speedrun.domain.core.ui.RoundedCornerBox
import com.speedrun.domain.kit.run.R
import com.speedrun.domain.repo.runs.model.RunModel
import com.speedrun.domain.core.designsystem.R as DesignSystemResources

@Composable
fun Run(
    run: RunModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {
            RoundedCornerBox(
                modifier = Modifier
                    .width(dimensionResource(R.dimen.game_image_width))
                    .height(dimensionResource(R.dimen.game_image_height))
            ) {
                AsyncImage(
                    model = run.game.assets.coverSmall.uri,
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(
                modifier = Modifier.width(dimensionResource(DesignSystemResources.dimen.side_padding))
            )
            Text(
                text = "Category: ${run.category.name}",
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

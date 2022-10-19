package com.speedrun.domain.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.speedrun.domain.core.designsystem.R

@Composable
fun Tile(
    title: String,
    modifier: Modifier,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        Divider(
            color = MaterialTheme.colorScheme.primary
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(MaterialTheme.colorScheme.secondary)
        ) {
            Spacer(
                modifier = Modifier.height(dimensionResource(R.dimen.side_padding))
            )
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = dimensionResource(R.dimen.side_padding))
            )
            Spacer(
                modifier = Modifier.height(dimensionResource(R.dimen.side_padding))
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(MaterialTheme.colorScheme.tertiary)
                .padding(horizontal = dimensionResource(R.dimen.side_padding))
        ) {
            content()
        }
    }
}

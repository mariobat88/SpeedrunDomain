package com.speedrun.domain.core.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RoundedCornerBox(
    modifier: Modifier = Modifier,
    clip: Shape = RoundedCornerShape(dimensionResource(R.dimen.rounded_corner_size)),
    borderWidth: Dp = 0.5.dp,
    borderColor: Color = Color.DarkGray,
    borderShape: Shape = RoundedCornerShape(dimensionResource(R.dimen.rounded_corner_size)),
    content: (@Composable BoxScope.() -> Unit)? = null,
) {
    Box(
        modifier = Modifier
            .clip(clip)
            .border(borderWidth, borderColor, borderShape)
            .then(modifier)
    ) {
        content?.invoke(this)
    }
}

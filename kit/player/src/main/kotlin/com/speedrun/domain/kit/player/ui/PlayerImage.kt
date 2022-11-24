package com.speedrun.domain.kit.player.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun PlayerImage(
    modifier: Modifier = Modifier,
    uri:String?,
) {
    uri?.let { imageUri ->
        AsyncImage(
            model = imageUri,
            contentDescription = "",
            modifier = modifier,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(4.dp))
    } ?: run {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "",
            modifier = modifier
        )
    }
}
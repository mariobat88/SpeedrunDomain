package com.speedrun.domain.kit.leaderboard

import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.speedrun.domain.kit.leaderboard.R as PlaceResources

@Composable
fun Place(
    place: Int?,
) {
    if (place != null) {
        Text(
            text = stringResource(PlaceResources.string.place, toOrdinal(place)),
            color = MaterialTheme.colorScheme.onBackground
        )
    } else {
        Text(
            text = "n/a",
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

fun toOrdinal(number: Int): String {
    return when (number % 10) {
        1 -> "${number}st"
        2 -> "${number}nd"
        3 -> "${number}rd"
        else -> "${number}th"
    }
}

package com.speedrun.domain.kit.leaderboard

import androidx.compose.material.Text
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.speedrun.domain.kit.leaderboard.R as PlaceResources

@Composable
fun OrdinalPlace(
    place: Int?,
    style: TextStyle = LocalTextStyle.current
) {
    if (place != null) {
        Text(
            text = toOrdinal(place),
            color = MaterialTheme.colorScheme.onBackground,
            style = style
        )
    } else {
        Text(
            text = "n/a",
            color = MaterialTheme.colorScheme.onBackground,
            style = style
        )
    }
}

@Composable
fun Place(
    place: Int?,
    style: TextStyle = LocalTextStyle.current
) {
    if (place != null) {
        Text(
            text = stringResource(PlaceResources.string.place, toOrdinal(place)),
            color = MaterialTheme.colorScheme.onBackground,
            style = style
        )
    } else {
        Text(
            text = "n/a",
            color = MaterialTheme.colorScheme.onBackground,
            style = style
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

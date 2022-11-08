package com.speedrun.domain.data.database.entities.embeds

import androidx.room.ColumnInfo

data class Names(
    @ColumnInfo(name = COLUMN_INTERNATION)
    val international: String,
    @ColumnInfo(name = COLUMN_JAPANESE)
    val japanese: String?
) {
    companion object {
        const val COLUMN_INTERNATION = "_nameInternational"
        const val COLUMN_JAPANESE = "_nameJapanese"
    }
}
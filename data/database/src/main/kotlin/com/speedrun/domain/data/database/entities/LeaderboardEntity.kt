package com.speedrun.domain.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.speedrun.data.common.enums.RunTimeEnum

@Entity(tableName = LeaderboardEntity.TABLE_NAME)
data class LeaderboardEntity(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    val id: String,
    @ColumnInfo(name = COLUMN_WEBLINK)
    val weblink: String,
    @ColumnInfo(name = COLUMN_GAME)
    val game: String,
    @ColumnInfo(name = COLUMN_CATEGORY)
    val category: String,
    @ColumnInfo(name = COLUMN_LEVEL)
    val level: String?,
    @ColumnInfo(name = COLUMN_PLATFORM)
    val platform: String?,
    @ColumnInfo(name = COLUMN_REGION)
    val region: String?,
    @ColumnInfo(name = COLUMN_EMULATORS)
    val emulators: Boolean?,
    @ColumnInfo(name = COLUMN_VIDEO_ONLY)
    val videoOnly: Boolean,
    @ColumnInfo(name = COLUMN_TIMING)
    val timing: RunTimeEnum,
) {
    companion object {
        const val TABLE_NAME = "leaderboard"
        const val COLUMN_ID = "${TABLE_NAME}_id"
        const val COLUMN_WEBLINK = "${TABLE_NAME}_weblink"
        const val COLUMN_GAME = "${TABLE_NAME}_game"
        const val COLUMN_CATEGORY = "${TABLE_NAME}_category"
        const val COLUMN_LEVEL = "${TABLE_NAME}_level"
        const val COLUMN_PLATFORM = "${TABLE_NAME}_platform"
        const val COLUMN_REGION = "${TABLE_NAME}_region"
        const val COLUMN_EMULATORS = "${TABLE_NAME}_emulators"
        const val COLUMN_VIDEO_ONLY = "${TABLE_NAME}_videoOnly"
        const val COLUMN_TIMING = "${TABLE_NAME}_timing"
    }
}

package com.speedrun.domain.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = LeaderboardPlaceEntity.TABLE_NAME,
    primaryKeys = [
        LeaderboardPlaceEntity.COLUMN_LEADERBOARD_ID,
        LeaderboardPlaceEntity.COLUMN_RUN_ID,
    ],
    indices = [
        Index(LeaderboardPlaceEntity.COLUMN_LEADERBOARD_ID),
        Index(LeaderboardPlaceEntity.COLUMN_RUN_ID)
    ]
)
data class LeaderboardPlaceEntity(
    @ColumnInfo(name = COLUMN_LEADERBOARD_ID)
    val leaderboardId: String,
    @ColumnInfo(name = COLUMN_RUN_ID)
    val runId: String,
    @ColumnInfo(name = COLUMN_PLACE)
    val place: Int,
) {
    companion object {
        const val TABLE_NAME = "leaderboardPlace"
        const val COLUMN_LEADERBOARD_ID = LeaderboardEntity.COLUMN_ID
        const val COLUMN_RUN_ID = RunEntity.COLUMN_ID
        const val COLUMN_PLACE = "${TABLE_NAME}_place"
    }
}

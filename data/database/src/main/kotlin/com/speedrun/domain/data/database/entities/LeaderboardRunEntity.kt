package com.speedrun.domain.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = LeaderboardRunEntity.TABLE_NAME,
    primaryKeys = [
        LeaderboardRunEntity.COLUMN_LEADERBOARD_ID,
        LeaderboardRunEntity.COLUMN_RUN_ID,
    ],
    indices = [
        Index(LeaderboardRunEntity.COLUMN_LEADERBOARD_ID),
        Index(LeaderboardRunEntity.COLUMN_RUN_ID)
    ]
)
data class LeaderboardRunEntity(
    @ColumnInfo(name = COLUMN_LEADERBOARD_ID)
    val leaderboardId: String,
    @ColumnInfo(name = COLUMN_RUN_ID)
    val runId: String,
) {
    companion object {
        const val TABLE_NAME = "leaderboardRun"
        const val COLUMN_LEADERBOARD_ID = "${TABLE_NAME}_leaderboardId"
        const val COLUMN_RUN_ID = "${TABLE_NAME}_runId"
    }
}

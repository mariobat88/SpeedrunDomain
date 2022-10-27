package com.speedrun.domain.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = LeaderboardPlaceEntity.TABLE_NAME,
    primaryKeys = [
        LeaderboardEntity.COLUMN_ID,
        RunEntity.COLUMN_ID,
    ],
    indices = [
        Index(LeaderboardEntity.COLUMN_ID),
        Index(RunEntity.COLUMN_ID)
    ]
)
data class LeaderboardPlaceEntity(
    @ColumnInfo(name = LeaderboardEntity.COLUMN_ID)
    val leaderboardId: String,
    @ColumnInfo(name = RunEntity.COLUMN_ID)
    val runId: String,
    @ColumnInfo(name = COLUMN_PLACE)
    val place: Int,
) {
    companion object {
        const val TABLE_NAME = "leaderboardPlace"
        const val COLUMN_PLACE = "${TABLE_NAME}_place"
    }
}

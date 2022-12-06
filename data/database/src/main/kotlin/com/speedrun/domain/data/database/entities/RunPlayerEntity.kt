package com.speedrun.domain.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = RunPlayerEntity.TABLE_NAME,
    primaryKeys = [
        RunPlayerEntity.COLUMN_RUN_ID,
        RunPlayerEntity.COLUMN_PLAYER_ID,
    ]
)
data class RunPlayerEntity(
    @ColumnInfo(name = COLUMN_RUN_ID)
    val runId: String,
    @ColumnInfo(name = COLUMN_PLAYER_ID)
    val playerId: String,
) {
    companion object {
        const val TABLE_NAME = "runPlayers"
        const val COLUMN_RUN_ID = "${TABLE_NAME}_runId"
        const val COLUMN_PLAYER_ID = "${TABLE_NAME}_playerId"
    }
}

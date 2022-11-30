package com.speedrun.domain.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import com.speedrun.data.common.enums.RunTimeEnum

@Entity(
    tableName = GameRunTimeEntity.TABLE_NAME,
    primaryKeys = [
        GameRunTimeEntity.COLUMN_GAME_ID,
        GameRunTimeEntity.COLUMN_RUN_TIME_ID,
    ],
    indices = [
        Index(GameRunTimeEntity.COLUMN_GAME_ID),
        Index(GameRunTimeEntity.COLUMN_RUN_TIME_ID)
    ]
)
data class GameRunTimeEntity(
    @ColumnInfo(name = COLUMN_GAME_ID)
    val gameId: String,
    @ColumnInfo(name = COLUMN_RUN_TIME_ID)
    val runTimeEnum: RunTimeEnum,
) {
    companion object {
        const val TABLE_NAME = "gameRunTime"
        const val COLUMN_GAME_ID = "${TABLE_NAME}_gameId"
        const val COLUMN_RUN_TIME_ID = "${TABLE_NAME}_runTimeId"
    }
}

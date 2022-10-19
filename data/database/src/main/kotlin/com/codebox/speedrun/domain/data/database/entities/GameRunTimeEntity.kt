package com.codebox.speedrun.domain.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.codebox.speedrun.data.common.enums.RunTimeEnum

@Entity(
    tableName = GameRunTimeEntity.TABLE_NAME,
    primaryKeys = [
        GameRunTimeEntity.COLUMN_GAME_ID,
        GameRunTimeEntity.COLUMN_RUN_TIME,
    ]
)
data class GameRunTimeEntity(
    @ColumnInfo(name = COLUMN_GAME_ID)
    val gameId: String,
    @ColumnInfo(name = COLUMN_RUN_TIME)
    val runTimeEnum: RunTimeEnum,
) {
    companion object {
        const val TABLE_NAME = "gameRunTime"
        const val COLUMN_GAME_ID = "${TABLE_NAME}_gameId"
        const val COLUMN_RUN_TIME = "${TABLE_NAME}_runTime"
    }
}

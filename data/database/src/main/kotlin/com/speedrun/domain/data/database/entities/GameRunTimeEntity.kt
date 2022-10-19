package com.speedrun.domain.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.speedrun.data.common.enums.RunTimeEnum

@Entity(
    tableName = GameRunTimeEntity.TABLE_NAME,
    primaryKeys = [
        GameEntity.COLUMN_ID,
        RunTimeEntity.COLUMN_ID,
    ]
)
data class GameRunTimeEntity(
    @ColumnInfo(name = GameEntity.COLUMN_ID)
    val gameId: String,
    @ColumnInfo(name = RunTimeEntity.COLUMN_ID)
    val runTimeEnum: RunTimeEnum,
) {
    companion object {
        const val TABLE_NAME = "gameRunTime"
    }
}

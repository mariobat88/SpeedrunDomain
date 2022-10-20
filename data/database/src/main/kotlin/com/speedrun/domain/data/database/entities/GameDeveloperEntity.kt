package com.speedrun.domain.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = GameDeveloperEntity.TABLE_NAME,
    primaryKeys = [
        GameEntity.COLUMN_ID,
        DeveloperEntity.COLUMN_ID,
    ],
    indices = [
        Index(GameEntity.COLUMN_ID),
        Index(DeveloperEntity.COLUMN_ID)
    ]
)
data class GameDeveloperEntity(
    @ColumnInfo(name = GameEntity.COLUMN_ID)
    val gameId: String,
    @ColumnInfo(name = DeveloperEntity.COLUMN_ID)
    val developerId: String,
) {
    companion object {
        const val TABLE_NAME = "gameDeveloper"
    }
}

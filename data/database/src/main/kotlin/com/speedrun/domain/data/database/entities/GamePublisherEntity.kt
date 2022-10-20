package com.speedrun.domain.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = GamePublisherEntity.TABLE_NAME,
    primaryKeys = [
        GameEntity.COLUMN_ID,
        PublisherEntity.COLUMN_ID,
    ],
    indices = [
        Index(GameEntity.COLUMN_ID),
        Index(PublisherEntity.COLUMN_ID)
    ]
)
data class GamePublisherEntity(
    @ColumnInfo(name = GameEntity.COLUMN_ID)
    val gameId: String,
    @ColumnInfo(name = PublisherEntity.COLUMN_ID)
    val publisherId: String,
) {
    companion object {
        const val TABLE_NAME = "gamePublisher"
    }
}

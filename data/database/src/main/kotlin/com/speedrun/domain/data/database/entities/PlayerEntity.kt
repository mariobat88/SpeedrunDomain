package com.speedrun.domain.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = PlayerEntity.TABLE_NAME)
data class PlayerEntity(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    val id: String,
    @ColumnInfo(name = COLUMN_REL)
    val rel: String,
) {
    companion object {
        const val TABLE_NAME = "player"
        const val COLUMN_ID = "${TABLE_NAME}_id"
        const val COLUMN_REL = "${TABLE_NAME}_rel"
    }
}

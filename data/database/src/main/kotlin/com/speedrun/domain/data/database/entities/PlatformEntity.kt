package com.speedrun.domain.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = PlatformEntity.TABLE_NAME)
data class PlatformEntity(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    val id: String,
    @ColumnInfo(name = COLUMN_NAME)
    val name: String,
    @ColumnInfo(name = COLUMN_RELEASED)
    val released: Int,
) {

    companion object {
        const val TABLE_NAME = "platform"
        const val COLUMN_ID = "${TABLE_NAME}_id"
        const val COLUMN_NAME = "${TABLE_NAME}_name"
        const val COLUMN_RELEASED = "${TABLE_NAME}_released"
    }
}

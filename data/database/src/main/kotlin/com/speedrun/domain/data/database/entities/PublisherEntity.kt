package com.speedrun.domain.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = PublisherEntity.TABLE_NAME)
data class PublisherEntity(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    val id: String,
    @ColumnInfo(name = COLUMN_NAME)
    val name: String,
) {
    companion object {
        const val TABLE_NAME = "publisher"
        const val COLUMN_ID = "${TABLE_NAME}_id"
        const val COLUMN_NAME = "${TABLE_NAME}_name"
    }
}

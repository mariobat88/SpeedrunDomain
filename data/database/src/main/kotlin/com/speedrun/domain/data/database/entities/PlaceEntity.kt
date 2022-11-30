package com.speedrun.domain.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = PlaceEntity.TABLE_NAME)
data class PlaceEntity(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_RUN_ID)
    val runId: String,
    @ColumnInfo(name = COLUMN_PLACE)
    val place: Int,
) {
    companion object {
        const val TABLE_NAME = "place"
        const val COLUMN_RUN_ID = RunEntity.COLUMN_ID
        const val COLUMN_PLACE = "${TABLE_NAME}_place"
    }
}

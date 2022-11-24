package com.speedrun.domain.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = VideoEntity.TABLE_NAME,
    primaryKeys = [VideoEntity.COLUMN_RUN_ID, VideoEntity.COLUMN_LINK]
)
data class VideoEntity(
    @ColumnInfo(name = COLUMN_RUN_ID)
    val runId: String,
    @ColumnInfo(name = COLUMN_LINK)
    val link: String,
) {

    companion object {
        const val TABLE_NAME = "video"
        const val COLUMN_RUN_ID = "${TABLE_NAME}_runId"
        const val COLUMN_LINK = "${TABLE_NAME}_link"
    }
}
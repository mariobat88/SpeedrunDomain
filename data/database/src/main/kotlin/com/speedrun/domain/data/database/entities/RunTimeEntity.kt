package com.speedrun.domain.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.speedrun.data.common.enums.RunTimeEnum

@Entity(tableName = RunTimeEntity.TABLE_NAME)
data class RunTimeEntity(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    val runTime: RunTimeEnum,
) {
    companion object {
        const val TABLE_NAME = "runTime"
        const val COLUMN_ID = "${TABLE_NAME}_id"
    }
}

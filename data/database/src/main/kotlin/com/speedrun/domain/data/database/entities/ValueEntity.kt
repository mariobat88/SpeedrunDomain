package com.speedrun.domain.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.speedrun.domain.data.database.entities.VariableValueEntity.Companion.COLUMN_VALUE_ID

@Entity(tableName = ValueEntity.TABLE_NAME)
data class ValueEntity(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_VALUE_ID)
    val id: String,
    @ColumnInfo(name = COLUMN_LABEL)
    val label: String,
){
    companion object{
        const val TABLE_NAME = "value"
        const val COLUMN_ID = "${TABLE_NAME}_id"
        const val COLUMN_LABEL = "${TABLE_NAME}_label"
    }
}

package com.speedrun.domain.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = VariableValueEntity.TABLE_NAME)
data class VariableValueEntity(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_VALUE_ID)
    val id: String,
    @ColumnInfo(name = COLUMN_LABEL)
    val label: String,
    @ColumnInfo(name = COLUMN_VARIABLE_ID)
    val variableId: String,
) {
    companion object{
        const val TABLE_NAME = "variableValue"
        const val COLUMN_VALUE_ID = "${TABLE_NAME}_valueId"
        const val COLUMN_LABEL = "${TABLE_NAME}_label"
        const val COLUMN_VARIABLE_ID = "${TABLE_NAME}_variableId"
    }
}

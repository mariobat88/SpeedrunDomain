package com.speedrun.domain.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = VariableValueEntity.TABLE_NAME,
    primaryKeys = [VariableValueEntity.COLUMN_VARIABLE_ID, VariableValueEntity.COLUMN_VALUE_ID],
    indices = [Index(VariableValueEntity.COLUMN_VARIABLE_ID), Index(VariableValueEntity.COLUMN_VALUE_ID)],
)
data class VariableValueEntity(
    @ColumnInfo(name = COLUMN_VARIABLE_ID)
    val variableId: String,
    @ColumnInfo(name = COLUMN_VALUE_ID)
    val valueId: String,
) {
    companion object {
        const val TABLE_NAME = "variableValue"
        const val COLUMN_VARIABLE_ID = "${TABLE_NAME}_variableId"
        const val COLUMN_VALUE_ID = "${TABLE_NAME}_valueId"
    }
}

package com.speedrun.domain.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = RunValueEntity.TABLE_NAME,
    primaryKeys = [RunValueEntity.COLUMN_RUN_ID, RunValueEntity.COLUMN_VARIABLE_ID, RunValueEntity.COLUMN_VALUE_ID]
)
data class RunValueEntity(
    @ColumnInfo(name = COLUMN_RUN_ID)
    val runId: String,
    @ColumnInfo(name = COLUMN_VARIABLE_ID)
    val variableId: String,
    @ColumnInfo(name = COLUMN_VALUE_ID)
    val valueId: String,
) {
    companion object {
        const val TABLE_NAME = "runValue"
        const val COLUMN_RUN_ID = RunEntity.COLUMN_ID
        const val COLUMN_VARIABLE_ID = VariableEntity.COLUMN_ID
        const val COLUMN_VALUE_ID = ValueEntity.COLUMN_ID
    }
}

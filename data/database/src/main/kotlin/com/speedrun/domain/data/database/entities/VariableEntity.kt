package com.speedrun.domain.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = VariableEntity.TABLE_NAME)
data class VariableEntity(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    val id: String,
    @ColumnInfo(name = COLUMN_NAME)
    val name: String,
    @ColumnInfo(name = COLUMN_CATEGORY)
    val category: String,
    @ColumnInfo(name = COLUMN_SCOPE)
    val scope: String,
    @ColumnInfo(name = COLUMN_MANDATORY)
    val mandatory: Boolean,
    @ColumnInfo(name = COLUMN_USER_DEFINED)
    val userDefined: Boolean,
    @ColumnInfo(name = COLUMN_OBSOLETES)
    val obsoletes: Boolean,
    @ColumnInfo(name = COLUMN_IS_SUBCATEGORY)
    val isSubcategory: Boolean,
) {
    companion object {
        const val TABLE_NAME = "variable"
        const val COLUMN_ID = "${TABLE_NAME}_id"
        const val COLUMN_NAME = "${TABLE_NAME}_name"
        const val COLUMN_CATEGORY = "${TABLE_NAME}_category"
        const val COLUMN_SCOPE = "${TABLE_NAME}_scope"
        const val COLUMN_MANDATORY = "${TABLE_NAME}_mandatory"
        const val COLUMN_USER_DEFINED = "${TABLE_NAME}_userDefined"
        const val COLUMN_OBSOLETES = "${TABLE_NAME}_obseoletes"
        const val COLUMN_IS_SUBCATEGORY = "${TABLE_NAME}_isSubcategory"
    }
}

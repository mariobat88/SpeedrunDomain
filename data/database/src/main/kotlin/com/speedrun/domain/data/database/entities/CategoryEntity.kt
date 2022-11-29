package com.speedrun.domain.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.speedrun.data.common.enums.RunTypeEnum

@Entity(tableName = CategoryEntity.TABLE_NAME)
data class CategoryEntity(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    val id: String,
    @ColumnInfo(name = COLUMN_NAME)
    val name: String,
    @ColumnInfo(name = COLUMN_WEBLINK)
    val weblink: String,
    @ColumnInfo(name = COLUMN_TYPE)
    val type: RunTypeEnum,
    @ColumnInfo(name = COLUMN_RULES)
    val rules: String?,
    @Embedded
    val players: Players,
    @ColumnInfo(name = COLUMN_MISCELLANEOUS)
    val miscellaneous: Boolean,
    @ColumnInfo(name = COLUMN_GAME_ID)
    val gameId: String,
) {
    companion object {
        const val TABLE_NAME = "category"
        const val COLUMN_ID = "${TABLE_NAME}_id"
        const val COLUMN_NAME = "${TABLE_NAME}_name"
        const val COLUMN_WEBLINK = "${TABLE_NAME}_weblink"
        const val COLUMN_TYPE = "${TABLE_NAME}_type"
        const val COLUMN_RULES = "${TABLE_NAME}_rules"
        const val COLUMN_MISCELLANEOUS = "${TABLE_NAME}_miscellaneous"
        const val COLUMN_GAME_ID = "${TABLE_NAME}_gameId"
    }

    data class Players(
        @ColumnInfo(name = COLUMN_TYPE)
        val type: String,
        @ColumnInfo(name = COLUMN_VALUE)
        val value: Int
    ) {
        companion object {
            const val COLUMN_TYPE = "${TABLE_NAME}_player_type"
            const val COLUMN_VALUE = "${TABLE_NAME}_player_value"
        }
    }
}

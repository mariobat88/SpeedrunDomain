package com.speedrun.domain.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.speedrun.domain.data.database.entities.embeds.Names

@Entity(tableName = RegionEntity.TABLE_NAME)
data class RegionEntity(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_CODE)
    val code: String,
    @Embedded(prefix = TABLE_NAME)
    val names: Names,
) {
    companion object {
        const val TABLE_NAME = "region"
        const val COLUMN_CODE = "code"
    }
}
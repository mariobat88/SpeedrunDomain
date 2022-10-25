package com.speedrun.domain.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = GuestEntity.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = PlayerEntity::class,
            parentColumns = [PlayerEntity.COLUMN_ID],
            childColumns = [GuestEntity.COLUMN_ID]
        )
    ],
)
data class GuestEntity(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    val id: String,
    @ColumnInfo(name = "name")
    val name: String,
) {
    companion object {
        const val TABLE_NAME = "guest"
        const val COLUMN_ID = "${TABLE_NAME}_id"
    }
}

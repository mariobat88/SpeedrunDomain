package com.speedrun.domain.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = UserLocationEntity.TABLE_NAME,
    primaryKeys = [
        UserLocationEntity.COLUMN_USER_ID,
        UserLocationEntity.COLUMN_LOCATION_ID
    ]
)
data class UserLocationEntity(
    @ColumnInfo(name = COLUMN_USER_ID)
    val userId: String,
    @ColumnInfo(name = COLUMN_LOCATION_ID)
    val locationId: String,
) {
    companion object {
        const val TABLE_NAME = "userLocation"
        const val COLUMN_USER_ID = UserEntity.COLUMN_ID
        const val COLUMN_LOCATION_ID = LocationEntity.COLUMN_ID
    }
}

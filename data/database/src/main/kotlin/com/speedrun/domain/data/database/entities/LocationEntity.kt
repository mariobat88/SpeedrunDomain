package com.speedrun.domain.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = LocationEntity.TABLE_NAME,
)
data class LocationEntity(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    val id:String, //Should be countryCode+regionCode
    @Embedded
    val country: Country,
    @Embedded
    val region: Region?
) {
    companion object {
        const val TABLE_NAME = "location"
        const val COLUMN_ID = "${TABLE_NAME}_id"
    }

    data class Country(
        @ColumnInfo(name = COLUMN_COUNTRY_CODE)
        val code: String,
        @ColumnInfo(name = COLUMN_INTERNATIONAL)
        val international: String,
        @ColumnInfo(name = COLUMN_JAPANESE)
        val japanese: String?,
    ) {
        companion object {
            const val COLUMN_COUNTRY_CODE = "${TABLE_NAME}_countryCode"
            const val COLUMN_INTERNATIONAL = "${TABLE_NAME}_countryInternational"
            const val COLUMN_JAPANESE = "${TABLE_NAME}_countryJapanese"
        }
    }

    data class Region(
        @ColumnInfo(name = COLUMN_REGION_CODE)
        val code: String,
        @ColumnInfo(name = COLUMN_INTERNATIONAL)
        val international: String,
        @ColumnInfo(name = COLUMN_JAPANESE)
        val japanese: String?,
    ) {
        companion object {
            const val COLUMN_REGION_CODE = "${TABLE_NAME}_regionCode"
            const val COLUMN_INTERNATIONAL = "${TABLE_NAME}_regionInternational"
            const val COLUMN_JAPANESE = "${TABLE_NAME}_regionJapanese"
        }
    }
}
package com.speedrun.domain.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = RunEntity.TABLE_NAME)
data class RunEntity(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    val id: String,
    @ColumnInfo(name = COLUMN_WEBLINK)
    val weblink: String,
    @ColumnInfo(name = COLUMN_GAME)
    val game: String,
    @ColumnInfo(name = COLUMN_LEVEL)
    val level: String?,
    @ColumnInfo(name = COLUMN_CATEGORY)
    val category: String,
    //val videos: VideosResponse?,
    @ColumnInfo(name = COLUMN_COMMENT)
    val comment: String?,
    @Embedded
    val status: Status,
    @ColumnInfo(name = COLUMN_DATE)
    val date: String?,
    @ColumnInfo(name = COLUMN_SUBMITTED)
    val submitted: String?,
    @Embedded
    val times: Times,
    @Embedded
    val system: System,
    //val splits: Any?,
    //val values: Map<String, String>?,
    //val links: List<LinkResponse>
) {
    companion object {
        const val TABLE_NAME = "run"
        const val COLUMN_ID = "${TABLE_NAME}_id"
        const val COLUMN_WEBLINK = "${TABLE_NAME}_weblink"
        const val COLUMN_GAME = "${TABLE_NAME}_game"
        const val COLUMN_LEVEL = "${TABLE_NAME}_level"
        const val COLUMN_CATEGORY = "${TABLE_NAME}_category"
        const val COLUMN_COMMENT = "${TABLE_NAME}_comment"
        const val COLUMN_DATE = "${TABLE_NAME}_date"
        const val COLUMN_SUBMITTED = "${TABLE_NAME}_submitted"
    }

    data class Status(
        @ColumnInfo(name = COLUMN_STATUS)
        val status: String,
        @ColumnInfo(name = COLUMN_EXAMINER)
        val examiner: String?,
        @ColumnInfo(name = COLUMN_VERIFY_DATE)
        val verifyDate: String?
    ) {
        companion object {
            const val COLUMN_STATUS = "${TABLE_NAME}_status"
            const val COLUMN_EXAMINER = "${TABLE_NAME}_examiner"
            const val COLUMN_VERIFY_DATE = "${TABLE_NAME}_verifyDate"
        }
    }

    data class Times(
        @ColumnInfo(name = COLUMN_PRIMARY)
        val primary: String,
        @ColumnInfo(name = COLUMN_PRIMARY_T)
        val primaryT: Float,
        @ColumnInfo(name = COLUMN_REALTIME)
        val realtime: String?,
        @ColumnInfo(name = COLUMN_REALTIME_T)
        val realtimeT: Float,
        @ColumnInfo(name = COLUMN_REALTIME_NO_LOADS)
        val realtimeNoLoads: String?,
        @ColumnInfo(name = COLUMN_REALTIME_NO_LOADS_T)
        val realtimeNoLoadsT: Float,
        @ColumnInfo(name = COLUMN_INGAME)
        val ingame: String?,
        @ColumnInfo(name = COLUMN_INGAME_T)
        val ingameT: Float
    ) {
        companion object {
            const val COLUMN_PRIMARY = "${TABLE_NAME}_primary"
            const val COLUMN_PRIMARY_T = "${TABLE_NAME}_primaryT"
            const val COLUMN_REALTIME = "${TABLE_NAME}_realtime"
            const val COLUMN_REALTIME_T = "${TABLE_NAME}_realtimeT"
            const val COLUMN_REALTIME_NO_LOADS = "${TABLE_NAME}_realtimeNoLoads"
            const val COLUMN_REALTIME_NO_LOADS_T = "${TABLE_NAME}_realtimeNoLoadsT"
            const val COLUMN_INGAME = "${TABLE_NAME}_ingame"
            const val COLUMN_INGAME_T = "${TABLE_NAME}_ingameT"
        }
    }

    data class System(
        @ColumnInfo(name = COLUMN_PLATFORM)
        val platform: String?,
        @ColumnInfo(name = COLUMN_EMULATED)
        val emulated: Boolean,
        @ColumnInfo(name = COLUMN_REGION)
        val region: String?
    ) {
        companion object {
            const val COLUMN_PLATFORM = "${TABLE_NAME}_platform"
            const val COLUMN_EMULATED = "${TABLE_NAME}_emulated"
            const val COLUMN_REGION = "${TABLE_NAME}_region"
        }
    }
}

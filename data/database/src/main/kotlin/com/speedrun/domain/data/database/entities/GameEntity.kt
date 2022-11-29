package com.speedrun.domain.data.database.entities

import androidx.room.*
import com.speedrun.data.common.enums.RunTimeEnum

@Entity(
    tableName = GameEntity.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = RunTimeEntity::class,
            parentColumns = [RunTimeEntity.COLUMN_ID],
            childColumns = [GameEntity.Ruleset.COLUMN_DEFAULT_TIME],
        )
    ],
    indices = [
        Index(GameEntity.Ruleset.COLUMN_DEFAULT_TIME)
    ]
)
data class GameEntity(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    val id: String,
    @Embedded
    val names: Names,
    val boostReceived: Int,
    val boostDistinctDonors: Int,
    val abbreviation: String,
    val weblink: String,
    val discord: String?,
    val released: Int,
    val releaseDate: String,
    @Embedded
    val ruleset: Ruleset,
    val romhack: Boolean,
    val created: String?,
    @Embedded
    val assets: Assets?,
) {
    companion object {
        const val TABLE_NAME = "game"
        const val COLUMN_ID = "${TABLE_NAME}_id"
    }

    data class Names(
        val international: String,
        val japanese: String?,
        val twitch: String?
    )

    data class Ruleset(
        @ColumnInfo(name = COLUMN_SHOW_MILLISECONDS)
        val showMilliseconds: Boolean,
        @ColumnInfo(name = COLUMN_REQUIRE_VERIFICATION)
        val requireVerification: Boolean,
        @ColumnInfo(name = COLUMN_REQUIRE_VIDEO)
        val requireVideo: Boolean,
        @ColumnInfo(name = COLUMN_DEFAULT_TIME)
        val defaultTime: RunTimeEnum,
        @ColumnInfo(name = COLUMN_EMULATORS_ALLOWED)
        val emulatorsAllowed: Boolean
    ) {
        companion object {
            const val COLUMN_SHOW_MILLISECONDS = "${TABLE_NAME}_ruleset_showMilliseconds"
            const val COLUMN_REQUIRE_VERIFICATION = "${TABLE_NAME}_ruleset_requireVerification"
            const val COLUMN_REQUIRE_VIDEO = "${TABLE_NAME}_ruleset_requireVideo"
            const val COLUMN_DEFAULT_TIME = "${TABLE_NAME}_ruleset_defaultTime"
            const val COLUMN_EMULATORS_ALLOWED = "${TABLE_NAME}_ruleset_emulatorsAllowed"
        }
    }

    data class Assets(
        @ColumnInfo(name = COLUMN_LOGO)
        val logo: String?,
        @ColumnInfo(name = COLUMN_COVER_TINY)
        val coverTiny: String?,
        @ColumnInfo(name = COLUMN_COVER_SMALL)
        val coverSmall: String?,
        @ColumnInfo(name = COLUMN_COVER_MEDIUM)
        val coverMedium: String?,
        @ColumnInfo(name = COLUMN_COVER_LARGE)
        val coverLarge: String?,
        @ColumnInfo(name = COLUMN_ICON)
        val icon: String?,
        @ColumnInfo(name = COLUMN_TROPHY_1ST)
        val trophy1st: String?,
        @ColumnInfo(name = COLUMN_TROPHY_2ND)
        val trophy2nd: String?,
        @ColumnInfo(name = COLUMN_TROPHY_3RD)
        val trophy3rd: String?,
        @ColumnInfo(name = COLUMN_TROPHY_4TH)
        val trophy4th: String?,
        @ColumnInfo(name = COLUMN_BACKGROUND)
        val background: String?,
        @ColumnInfo(name = COLUMN_FOREGROUND)
        val foreground: String?
    ) {
        companion object {
            const val COLUMN_LOGO = "${TABLE_NAME}_asset_logo"
            const val COLUMN_COVER_TINY = "${TABLE_NAME}_asset_coverTiny"
            const val COLUMN_COVER_SMALL = "${TABLE_NAME}_asset_coverSmall"
            const val COLUMN_COVER_MEDIUM = "${TABLE_NAME}_asset_coverMedium"
            const val COLUMN_COVER_LARGE = "${TABLE_NAME}_asset_coverLarge"
            const val COLUMN_ICON = "${TABLE_NAME}_asset_icon"
            const val COLUMN_TROPHY_1ST = "${TABLE_NAME}_asset_trophy1st"
            const val COLUMN_TROPHY_2ND = "${TABLE_NAME}_asset_trophy2nd"
            const val COLUMN_TROPHY_3RD = "${TABLE_NAME}_asset_trophy3rd"
            const val COLUMN_TROPHY_4TH = "${TABLE_NAME}_asset_trophy4th"
            const val COLUMN_BACKGROUND = "${TABLE_NAME}_asset_background"
            const val COLUMN_FOREGROUND = "${TABLE_NAME}_asset_foreground"
        }
    }
}

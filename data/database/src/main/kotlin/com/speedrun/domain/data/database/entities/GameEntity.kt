package com.speedrun.domain.data.database.entities

import androidx.room.*
import com.speedrun.data.common.enums.RunTimeEnum
import com.speedrun.domain.data.database.entities.embeds.Names

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
    //val gametypes: List<String>,
    //val platforms: List<String>,
    //val regions: List<String>,
    //val genres: List<String>,
    //val engines: List<String>,
    //val developers: List<String>,
    //val publishers: List<String>,
    //val moderators: Map<String, String>,
    val created: String?,
    @Embedded
    val assets: Assets?,
    //val links: List<LinkModel>
) {
    companion object {
        const val TABLE_NAME = "game"
        const val COLUMN_ID = "${TABLE_NAME}_id"
    }

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
            const val COLUMN_SHOW_MILLISECONDS = "showMilliseconds"
            const val COLUMN_REQUIRE_VERIFICATION = "requireVerification"
            const val COLUMN_REQUIRE_VIDEO = "requireVideo"
            const val COLUMN_DEFAULT_TIME = "defaultTime"
            const val COLUMN_EMULATORS_ALLOWED = "emulatorsAllowed"
        }
    }

    data class Assets(
        @ColumnInfo(name = "logo")
        val logo: String?,
        @ColumnInfo(name = "coverTiny")
        val coverTiny: String?,
        @ColumnInfo(name = "coverSmall")
        val coverSmall: String?,
        @ColumnInfo(name = "coverMedium")
        val coverMedium: String?,
        @ColumnInfo(name = "coverLarge")
        val coverLarge: String?,
        @ColumnInfo(name = "icon")
        val icon: String?,
        @ColumnInfo(name = "trophy1st")
        val trophy1st: String?,
        @ColumnInfo(name = "trophy2nd")
        val trophy2nd: String?,
        @ColumnInfo(name = "trophy3rd")
        val trophy3rd: String?,
        @ColumnInfo(name = "trophy4th")
        val trophy4th: String?,
        @ColumnInfo(name = "background")
        val background: String?,
        @ColumnInfo(name = "foreground")
        val foreground: String?
    )
}
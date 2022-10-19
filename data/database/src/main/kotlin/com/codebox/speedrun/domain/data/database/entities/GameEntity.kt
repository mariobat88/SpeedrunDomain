package com.codebox.speedrun.domain.data.database.entities

import androidx.room.*
import com.codebox.speedrun.data.common.enums.RunTimeEnum

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
    val assets: Assets,
    //val links: List<LinkModel>
) {
    companion object {
        const val TABLE_NAME = "games"
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
            const val COLUMN_SHOW_MILLISECONDS = "showMilliseconds"
            const val COLUMN_REQUIRE_VERIFICATION = "requireVerification"
            const val COLUMN_REQUIRE_VIDEO = "requireVideo"
            const val COLUMN_DEFAULT_TIME = "defaultTime"
            const val COLUMN_EMULATORS_ALLOWED = "emulatorsAllowed"
        }
    }

    data class Assets(
        @Embedded(prefix = "logo")
        val logo: Asset,
        @Embedded(prefix = "coverTiny")
        val coverTiny: Asset,
        @Embedded(prefix = "coverSmall")
        val coverSmall: Asset,
        @Embedded(prefix = "coverMedium")
        val coverMedium: Asset,
        @Embedded(prefix = "coverLarge")
        val coverLarge: Asset,
        @Embedded(prefix = "icon")
        val icon: Asset,
        @Embedded(prefix = "trophy1st")
        val trophy1st: Asset,
        @Embedded(prefix = "trophy2nd")
        val trophy2nd: Asset,
        @Embedded(prefix = "trophy3rd")
        val trophy3rd: Asset,
        @Embedded(prefix = "trophy4th")
        val trophy4th: Asset?,
        @Embedded(prefix = "background")
        val background: Asset?,
        @Embedded(prefix = "foreground")
        val foreground: Asset?
    ) {
        data class Asset(
            val uri: String?
        )
    }
}
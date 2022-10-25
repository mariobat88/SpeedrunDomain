package com.speedrun.domain.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = UserEntity.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = PlayerEntity::class,
            parentColumns = [PlayerEntity.COLUMN_ID],
            childColumns = [UserEntity.COLUMN_ID]
        )
    ],
)
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    val id: String,
    @Embedded(prefix = PREFIX_NAMES)
    val names: Names,
    @ColumnInfo(name = COLUMN_WEBLINK)
    val weblink: String,
    @Embedded(prefix = PREFIX_NAME_STYLE)
    val nameStyle: NameStyle,
    @ColumnInfo(name = COLUMN_ROLE)
    val role: String,
    @ColumnInfo(name = COLUMN_SIGNUP)
    val signup: String?,
//    @ColumnInfo(name = COLUMN_ID)
//    val location: Location?,
    @ColumnInfo(name = COLUMN_TWITCH)
    val twitch: String?,
    @ColumnInfo(name = COLUMN_HITBOX)
    val hitbox: String?,
    @ColumnInfo(name = COLUMN_YOUTUBE)
    val youtube: String?,
    @ColumnInfo(name = COLUMN_TWITTER)
    val twitter: String?,
    @ColumnInfo(name = COLUMN_SPEEDRUNSLIVE)
    val speedrunslive: String?,
    @ColumnInfo(name = COLUMN_ICON)
    val icon: String?,
    @ColumnInfo(name = COLUMN_SUPPORTER_ICON)
    val supporterIcon: String?,
    @ColumnInfo(name = COLUMN_IMAGE)
    val image: String?,
) {
    companion object {
        const val TABLE_NAME = "user"
        const val COLUMN_ID = "${TABLE_NAME}_id"
        const val COLUMN_WEBLINK = "${TABLE_NAME}_weblink"
        const val COLUMN_ROLE = "${TABLE_NAME}_role"
        const val COLUMN_SIGNUP = "${TABLE_NAME}_signup"
        const val COLUMN_TWITCH = "${TABLE_NAME}_twitch"
        const val COLUMN_HITBOX = "${TABLE_NAME}_hitbox"
        const val COLUMN_YOUTUBE = "${TABLE_NAME}_youtube"
        const val COLUMN_TWITTER = "${TABLE_NAME}_twitter"
        const val COLUMN_SPEEDRUNSLIVE = "${TABLE_NAME}_speedrunslive"
        const val COLUMN_ICON = "${TABLE_NAME}_icon"
        const val COLUMN_SUPPORTER_ICON = "${TABLE_NAME}_supporterIcon"
        const val COLUMN_IMAGE = "${TABLE_NAME}_image"
        const val PREFIX_NAMES = "${TABLE_NAME}_name"
        const val PREFIX_NAME_STYLE = "${TABLE_NAME}_nameStyle"
    }

    data class Names(
        @ColumnInfo(name = COLUMN_INTERNATION)
        val international: String,
        @ColumnInfo(name = COLUMN_JAPANESE)
        val japanese: String?
    ) {
        companion object {
            const val COLUMN_INTERNATION = "International"
            const val COLUMN_JAPANESE = "Japanese"
        }
    }

    data class NameStyle(
        @ColumnInfo(name = COLUMN_STYLE)
        val style: String,
        @Embedded(prefix = PREFIX_COLOR)
        val color: Color?,
        @Embedded(prefix = PREFIX_COLOR_FROM)
        val colorFrom: Color?,
        @Embedded(prefix = PREFIX_COLOR_TO)
        val colorTo: Color?
    ) {
        companion object {
            const val COLUMN_STYLE = "Style"
            const val PREFIX_COLOR = "Color"
            const val PREFIX_COLOR_FROM = "ColorFrom"
            const val PREFIX_COLOR_TO = "ColorTo"
        }

        data class Color(
            @Embedded(prefix = COLUMN_LIGHT)
            val light: String,
            @Embedded(prefix = COLUMN_DARK)
            val dark: String
        ) {
            companion object {
                const val COLUMN_LIGHT = "Light"
                const val COLUMN_DARK = "Dark"
            }
        }
    }
}
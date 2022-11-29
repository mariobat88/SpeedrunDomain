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
    @Embedded
    val names: Names,
    @ColumnInfo(name = COLUMN_WEBLINK)
    val weblink: String,
    @Embedded
    val nameStyle: NameStyle,
    @ColumnInfo(name = COLUMN_ROLE)
    val role: String,
    @ColumnInfo(name = COLUMN_SIGNUP)
    val signup: String?,
    @ColumnInfo(name = COLUMN_LOCATION)
    val location: String?,
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
        const val COLUMN_LOCATION = "${TABLE_NAME}_location"
        const val COLUMN_TWITCH = "${TABLE_NAME}_twitch"
        const val COLUMN_HITBOX = "${TABLE_NAME}_hitbox"
        const val COLUMN_YOUTUBE = "${TABLE_NAME}_youtube"
        const val COLUMN_TWITTER = "${TABLE_NAME}_twitter"
        const val COLUMN_SPEEDRUNSLIVE = "${TABLE_NAME}_speedrunslive"
        const val COLUMN_ICON = "${TABLE_NAME}_icon"
        const val COLUMN_SUPPORTER_ICON = "${TABLE_NAME}_supporterIcon"
        const val COLUMN_IMAGE = "${TABLE_NAME}_image"
    }

    data class Names(
        @ColumnInfo(name = COLUMN_INTERNATION)
        val international: String,
        @ColumnInfo(name = COLUMN_JAPANESE)
        val japanese: String?
    ) {
        companion object {
            const val COLUMN_INTERNATION = "${TABLE_NAME}_name_international"
            const val COLUMN_JAPANESE = "${TABLE_NAME}_name_japanese"
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
            const val COLUMN_STYLE = "${TABLE_NAME}_nameStyle_style"
            const val PREFIX_COLOR = "${TABLE_NAME}_nameStyle_color"
            const val PREFIX_COLOR_FROM = "${TABLE_NAME}_nameStyle_colorFrom"
            const val PREFIX_COLOR_TO = "${TABLE_NAME}_nameStyle_colorTo"
        }

        data class Color(
            @ColumnInfo(name = COLUMN_LIGHT)
            val light: String,
            @ColumnInfo(name = COLUMN_DARK)
            val dark: String
        ) {
            companion object {
                const val COLUMN_LIGHT = "Light"
                const val COLUMN_DARK = "Dark"
            }
        }
    }
}
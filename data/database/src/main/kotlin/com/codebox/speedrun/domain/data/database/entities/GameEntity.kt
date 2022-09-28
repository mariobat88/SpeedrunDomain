package com.codebox.speedrun.domain.data.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GameEntity(
    @PrimaryKey
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
    //val assets: Assets,
    //val links: List<LinkModel>
) {
    data class Names(
        val international: String,
        val japanese: String?,
        val twitch: String?
    )

    data class Ruleset(
        val showMilliseconds: Boolean,
        val requireVerification: Boolean,
        val requireVideo: Boolean,
        //val runTimes: List<String>,
        val defaultTime: String,
        val emulatorsAllowed: Boolean
    )
}
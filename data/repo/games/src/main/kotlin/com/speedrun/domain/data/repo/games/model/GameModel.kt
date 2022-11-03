package com.speedrun.domain.data.repo.games.model

import com.speedrun.data.common.enums.RunTimeEnum
import com.speedrun.domain.data.repo.common.model.LinkModel

data class GameModel(
    val id: String,
    val names: Names,
    val boostReceived: Int,
    val boostDistinctDonors: Int,
    val abbreviation: String,
    val weblink: String,
    val discord: String?,
    val released: Int,
    val releaseDate: String,
    val ruleset: Ruleset,
    val romhack: Boolean,
    val gametypes: List<String>,
    val platforms: List<String>,
    val regions: List<String>,
    val genres: List<String>,
    val engines: List<String>,
    val developers: List<String>?,
    val publishers: List<String>?,
    val moderators: Map<String, String>,
    val created: String?,
    val assets: Assets?,
    val links: List<LinkModel>
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
        val runTimes: List<RunTimeEnum>?,
        val defaultTime: RunTimeEnum,
        val emulatorsAllowed: Boolean
    )

    data class Assets(
        val logo: String?,
        val coverTiny: String?,
        val coverSmall: String?,
        val coverMedium: String?,
        val coverLarge: String?,
        val icon: String?,
        val trophy1st: String?,
        val trophy2nd: String?,
        val trophy3rd: String?,
        val trophy4th: String?,
        val background: String?,
        val foreground: String?
    )
}

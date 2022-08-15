package com.codebox.speedrundomain.data.repo.games.model

import com.codebox.speedrun.data.repo.common.model.LinkModel

data class GameModel(
    val id: String,
    val names: Names,
    val boostReceived: Int,
    val boostDistinctDonors: Int,
    val abbreviation: String,
    val weblink: String,
    val discord: String,
    val released: Int,
    val releaseDate: String,
    val ruleset: Ruleset,
    val romhack: Boolean,
    val gametypes: List<String>,
    val platforms: List<String>,
    val regions: List<String>,
    val genres: List<String>,
    val engines: List<String>,
    val developers: List<String>,
    val publishers: List<String>,
    val moderators: Map<String, String>,
    val created: String?,
    val assets: Assets,
    val links: List<LinkModel>
) {
    data class Names(
        val international: String,
        val japanese: String?,
        val twitch: String
    )

    data class Ruleset(
        val showMilliseconds: Boolean,
        val requireVerification: Boolean,
        val requireVideo: Boolean,
        val runTimes: List<String>,
        val defaultTime: String,
        val emulatorsAllowed: Boolean
    )

    data class Assets(
        val logo: Asset,
        val coverTiny: Asset,
        val coverSmall: Asset,
        val coverMedium: Asset,
        val coverLarge: Asset,
        val icon: Asset,
        val trophy1st: Asset,
        val trophy2nd: Asset,
        val trophy3rd: Asset,
        val trophy4th: Asset,
        val background: Asset,
        val foreground: Asset
    ) {
        data class Asset(
            val uri: String?
        )
    }
}

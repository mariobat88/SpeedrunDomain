package com.speedrun.domain.data.repo.players.model

import com.speedrun.domain.data.repo.common.model.LinkModel
import com.speedrun.domain.data.repo.common.model.NamesModel

sealed class PlayerModel {
    data class UserModel(
        val id: String,
        val names: NamesModel,
        val weblink: String,
        val nameStyle: NameStyle,
        val role: String,
        val signup: String?,
        val country: CountryModel?,
        val region: RegionModel?,
        val twitch: String?,
        val hitbox: String?,
        val youtube: String?,
        val twitter: String?,
        val speedrunslive: String?,
        val icon: String?,
        val supporterIcon: String?,
        val image: String?,
        val links: List<LinkModel>
    ) : PlayerModel() {
        data class NameStyle(
            val style: String,
            val color: Color?,
            val colorFrom: Color?,
            val colorTo: Color?
        ) {
            data class Color(
                val light: String,
                val dark: String
            )
        }

        data class Location(
            val country: Country,
            val region: Region?
        ) {
            data class Country(
                val code: String,
                val names: NamesModel
            )

            data class Region(
                val code: String,
                val names: NamesModel
            )
        }

        data class Assets(
            val icon: Asset?,
            val supporterIcon: Asset?,
            val image: Asset?,
        ) {
            data class Asset(
                val uri: String?,
            )
        }
    }

    data class GuestModel(
        val name: String,
        val links: List<LinkModel>
    ) : PlayerModel()
}

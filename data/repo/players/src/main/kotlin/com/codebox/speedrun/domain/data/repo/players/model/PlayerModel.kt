package com.codebox.speedrun.domain.data.repo.players.model

import com.codebox.speedrun.domain.data.repo.common.model.LinkModel

sealed class PlayerModel {
    data class UserModel(
        val id: String,
        val names: NamesModel,
        val weblink: String,
        val nameStyle: NameStyle,
        val role: String,
        val signup: String?,
        val location: Location?,
        val twitch: LinkModel?,
        val hitbox: LinkModel?,
        val youtube: LinkModel?,
        val twitter: LinkModel?,
        val speedrunslive: LinkModel?,
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
    }

    data class GuestModel(
        val name: String,
        val links: List<LinkModel>
    ) : PlayerModel()
}
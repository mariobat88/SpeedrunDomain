package com.speedrun.domain.data.datasource.players.mapper

import com.speedrun.domain.data.database.entities.GuestEntity
import com.speedrun.domain.data.database.entities.LocationEntity
import com.speedrun.domain.data.database.entities.UserEntity
import com.speedrun.domain.data.database.result.PlayerResult
import com.speedrun.domain.data.repo.players.model.NamesModel
import com.speedrun.domain.data.repo.players.model.PlayerModel
import com.speedrun.domain.networking.api.players.PlayerType

fun PlayerResult.toPlayerModel(): PlayerModel {
    return if (player.rel == PlayerType.user.name) {
        userResult.user?.toUserModel(userResult.location)!!
    } else {
        guest?.toGuestModel()!!
    }
}

private fun UserEntity.toUserModel(
    location: LocationEntity?,
) = PlayerModel.UserModel(
    id = id,
    names = names.toModel(),
    weblink = weblink,
    nameStyle = nameStyle.toModel(),
    role = role,
    signup = signup,
    location = location?.toModel(),
    twitch = twitch,
    hitbox = hitbox,
    youtube = youtube,
    twitter = twitter,
    speedrunslive = speedrunslive,
    links = emptyList(),
    icon = icon,
    supporterIcon = supporterIcon,
    image = image,
)

private fun UserEntity.Names.toModel() = NamesModel(
    international = international,
    japanese = japanese,
)

private fun UserEntity.NameStyle.toModel() =
    PlayerModel.UserModel.NameStyle(
        style = style,
        color = color?.toModel(),
        colorFrom = colorFrom?.toModel(),
        colorTo = colorTo?.toModel(),
    )

private fun UserEntity.NameStyle.Color.toModel() =
    PlayerModel.UserModel.NameStyle.Color(
        light = light,
        dark = dark
    )

fun GuestEntity.toGuestModel() = PlayerModel.GuestModel(
    name = name,
    links = emptyList(),
)

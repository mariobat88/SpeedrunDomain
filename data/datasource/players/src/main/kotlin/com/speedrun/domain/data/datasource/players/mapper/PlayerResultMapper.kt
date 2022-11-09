package com.speedrun.domain.data.datasource.players.mapper

import com.speedrun.domain.data.database.entities.CountryEntity
import com.speedrun.domain.data.database.entities.GuestEntity
import com.speedrun.domain.data.database.entities.RegionEntity
import com.speedrun.domain.data.database.entities.UserEntity
import com.speedrun.domain.data.database.result.PlayerResult
import com.speedrun.domain.data.datasource.common.mapper.toNamesModel
import com.speedrun.domain.data.repo.players.model.PlayerModel
import com.speedrun.domain.networking.api.players.PlayerType

fun PlayerResult.toPlayerModel(): PlayerModel {
    return if (player.rel == PlayerType.user.name) {
        userResult?.user?.toUserModel(
            countryEntity = userResult?.countryEntity,
            regionEntity = null
        )!!
    } else {
        guest?.toGuestModel()!!
    }
}

private fun UserEntity.toUserModel(
    countryEntity: CountryEntity?,
    regionEntity: RegionEntity?
) = PlayerModel.UserModel(
    id = id,
    names = names.toNamesModel(),
    weblink = weblink,
    nameStyle = nameStyle.toModel(),
    role = role,
    signup = signup,
    country = countryEntity?.toCountryModel(),
    region = regionEntity?.toRegionModel(),
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

package com.codebox.speedrun.domain.data.datasource.players.mapper

import com.codebox.speedrun.domain.data.datasource.common.mapper.toModel
import com.codebox.speedrun.domain.data.pagination.PaginationModel
import com.codebox.speedrun.domain.data.pagination.toModel
import com.codebox.speedrun.domain.data.repo.players.model.PlayerModel
import com.codebox.speedrun.domain.networking.api.pagination.PaginationResponse
import com.codebox.speedrun.domain.networking.api.players.UserResponse

fun PaginationResponse<UserResponse>.toModel() = PaginationModel(
    data = data.map { it.toModel() },
    pagination = pagination.toModel()
)

fun UserResponse.toModel() = PlayerModel.UserModel(
    id = id,
    names = names.toModel(),
    weblink = weblink,
    nameStyle = nameStyle.toModel(),
    role = role,
    signup = signup,
    location = location?.toModel(),
    twitch = twitch?.toModel(),
    hitbox = hitbox?.toModel(),
    youtube = youtube?.toModel(),
    twitter = twitter?.toModel(),
    speedrunslive = speedrunslive?.toModel(),
    assets = assets.toModel(),
    links = links.map { it.toModel() },
)

private fun UserResponse.NameStyle.toModel() = PlayerModel.UserModel.NameStyle(
    style = style,
    color = color?.toModel(),
    colorFrom = colorFrom?.toModel(),
    colorTo = colorTo?.toModel(),
)

private fun UserResponse.NameStyle.Color.toModel() = PlayerModel.UserModel.NameStyle.Color(
    light = light,
    dark = dark
)

private fun UserResponse.Location.toModel() = PlayerModel.UserModel.Location(
    country = country.toModel(),
    region = region?.toModel(),
)

private fun UserResponse.Location.Country.toModel() =
    PlayerModel.UserModel.Location.Country(
        code = code,
        names = names.toModel(),
    )

private fun UserResponse.Location.Region.toModel() =
    PlayerModel.UserModel.Location.Region(
        code = code,
        names = names.toModel(),
    )

private fun UserResponse.Assets.toModel() = PlayerModel.UserModel.Assets(
    icon = icon?.toModel(),
    supporterIcon = supporterIcon?.toModel(),
    image = image?.toModel(),
)

private fun UserResponse.Assets.Asset.toModel() = PlayerModel.UserModel.Assets.Asset(
    uri = uri,
)

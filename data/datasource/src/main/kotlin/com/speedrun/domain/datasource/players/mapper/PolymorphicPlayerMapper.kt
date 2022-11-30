package com.speedrun.domain.datasource.players.mapper

import com.speedrun.domain.api.pagination.models.PaginationResponse
import com.speedrun.domain.api.players.models.PlayerType
import com.speedrun.domain.api.players.models.PolymorphicPlayerResponse
import com.speedrun.domain.data.database.entities.GuestEntity
import com.speedrun.domain.data.database.entities.PlayerEntity
import com.speedrun.domain.data.database.entities.UserEntity
import com.speedrun.domain.datasource.common.mapper.toModel
import com.speedrun.domain.datasource.pagination.mapper.toModel
import com.speedrun.domain.repo.pagination.model.PaginationModel
import com.speedrun.domain.repo.players.model.PlayerModel

fun PolymorphicPlayerResponse.toPlayerModel(): PlayerModel {
    return if (playerType == PlayerType.user) {
        with(this as PolymorphicPlayerResponse.UserResponse) {
            this.toUserModel()
        }
    } else {
        with(this as PolymorphicPlayerResponse.GuestResponse) {
            this.toGuestModel()
        }
    }
}

fun PaginationResponse<PolymorphicPlayerResponse.UserResponse>.toPaginationModel() =
    PaginationModel(
        data = data.map { it.toUserModel() },
        pagination = pagination.toModel()
    )

fun PolymorphicPlayerResponse.UserResponse.toPlayerEntity() = PlayerEntity(
    id = id,
    rel = "user",
)

fun PolymorphicPlayerResponse.GuestResponse.toPlayerEntity() = PlayerEntity(
    id = name,
    rel = "guest",
)

fun PolymorphicPlayerResponse.UserResponse.toUserModel() = PlayerModel.UserModel(
    id = id,
    names = names.toModel(),
    weblink = weblink,
    nameStyle = nameStyle.toModel(),
    role = role,
    signup = signup,
    location = location?.toModel(),
    twitch = twitch?.uri,
    hitbox = hitbox?.uri,
    youtube = youtube?.uri,
    twitter = twitter?.uri,
    speedrunslive = speedrunslive?.uri,
    icon = assets.icon?.uri,
    supporterIcon = assets.supporterIcon?.uri,
    image = assets.image?.uri,
)

fun PolymorphicPlayerResponse.GuestResponse.toGuestModel() = PlayerModel.GuestModel(
    name = name,
    links = links.map { it.toModel() }
)

private fun PolymorphicPlayerResponse.UserResponse.NameStyle.toModel() =
    PlayerModel.UserModel.NameStyle(
        style = style,
        color = color?.toModel(),
        colorFrom = colorFrom?.toModel(),
        colorTo = colorTo?.toModel(),
    )

private fun PolymorphicPlayerResponse.UserResponse.NameStyle.Color.toModel() =
    PlayerModel.UserModel.NameStyle.Color(
        light = light,
        dark = dark
    )

private fun PolymorphicPlayerResponse.UserResponse.Location.toModel() =
    PlayerModel.UserModel.Location(
        country = country.toModel(),
        region = region?.toModel(),
    )

private fun PolymorphicPlayerResponse.UserResponse.Location.Country.toModel() =
    PlayerModel.UserModel.Location.Country(
        code = code,
        international = names.international,
        japanese = names.japanese,
    )

private fun PolymorphicPlayerResponse.UserResponse.Location.Region.toModel() =
    PlayerModel.UserModel.Location.Region(
        code = code,
        international = names.international,
        japanese = names.japanese,
    )

private fun PolymorphicPlayerResponse.UserResponse.Assets.toModel() = PlayerModel.UserModel.Assets(
    icon = icon?.toModel(),
    supporterIcon = supporterIcon?.toModel(),
    image = image?.toModel(),
)

private fun PolymorphicPlayerResponse.UserResponse.Assets.Asset.toModel() =
    PlayerModel.UserModel.Assets.Asset(
        uri = uri,
    )

fun PolymorphicPlayerResponse.UserResponse.toUserEntity() = UserEntity(
    id = id,
    names = names.toNamesEntity(),
    weblink = weblink,
    nameStyle = nameStyle.toEntity(),
    role = role,
    signup = signup,
    location = location?.createLocationId(),
    twitch = twitch?.uri,
    hitbox = hitbox?.uri,
    youtube = youtube?.uri,
    twitter = twitter?.uri,
    speedrunslive = speedrunslive?.uri,
    icon = assets.icon?.uri,
    supporterIcon = assets.supporterIcon?.uri,
    image = assets.image?.uri,
)

private fun PolymorphicPlayerResponse.UserResponse.NameStyle.toEntity() = UserEntity.NameStyle(
    style = style,
    color = color?.toEntity(),
    colorFrom = colorFrom?.toEntity(),
    colorTo = colorTo?.toEntity(),
)

private fun PolymorphicPlayerResponse.UserResponse.NameStyle.Color.toEntity() =
    UserEntity.NameStyle.Color(
        light = light,
        dark = dark
    )

fun PolymorphicPlayerResponse.GuestResponse.toGuestEntity() = GuestEntity(
    id = name,
    name = name,
)


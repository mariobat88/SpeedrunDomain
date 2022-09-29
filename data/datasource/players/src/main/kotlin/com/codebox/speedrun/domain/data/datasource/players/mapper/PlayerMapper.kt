package com.codebox.speedrun.domain.data.datasource.players.mapper

import com.codebox.speedrun.domain.data.datasource.common.mapper.toModel
import com.codebox.speedrun.domain.data.pagination.PaginationModel
import com.codebox.speedrun.domain.data.pagination.toModel
import com.codebox.speedrun.domain.data.repo.players.model.PlayerModel
import com.codebox.speedrun.domain.networking.api.pagination.PaginationResponse
import com.codebox.speedrun.domain.networking.api.players.PlayerResponse
import com.codebox.speedrun.domain.networking.api.players.PlayerType

fun PaginationResponse<PlayerResponse>.toModel() = PaginationModel(
    data = data.map { it.toModel() },
    pagination = pagination.toModel()
)

fun PlayerResponse.toModel(): PlayerModel {
    return if (playerType == PlayerType.user) {
        with(this as PlayerResponse.UserResponse) {
            PlayerModel.UserModel(
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
                links = links.map { it.toModel() },
            )
        }
    } else {
        with(this as PlayerResponse.GuestResponse) {
            PlayerModel.GuestModel(
                name = name,
                links = links.map { it.toModel() }
            )
        }
    }
}

private fun PlayerResponse.UserResponse.NameStyle.toModel() = PlayerModel.UserModel.NameStyle(
    style = style,
    color = color?.toModel(),
    colorFrom = colorFrom?.toModel(),
    colorTo = colorTo?.toModel(),
)

private fun PlayerResponse.UserResponse.NameStyle.Color.toModel() =
    PlayerModel.UserModel.NameStyle.Color(
        light = light,
        dark = dark
    )

private fun PlayerResponse.UserResponse.Location.toModel() = PlayerModel.UserModel.Location(
    country = country.toModel(),
    region = region?.toModel(),
)

private fun PlayerResponse.UserResponse.Location.Country.toModel() =
    PlayerModel.UserModel.Location.Country(
        code = code,
        names = names.toModel(),
    )

private fun PlayerResponse.UserResponse.Location.Region.toModel() =
    PlayerModel.UserModel.Location.Region(
        code = code,
        names = names.toModel(),
    )

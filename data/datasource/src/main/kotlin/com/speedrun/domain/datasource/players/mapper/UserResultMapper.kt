package com.speedrun.domain.datasource.players.mapper

import com.speedrun.domain.data.database.result.UserResult
import com.speedrun.domain.repo.players.model.PlayerModel

fun UserResult.toUserModel(): PlayerModel.UserModel {
    val location = locationEntity
    return with(userEntity!!) {
        PlayerModel.UserModel(
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
            icon = icon,
            supporterIcon = supporterIcon,
            image = image,
        )
    }
}

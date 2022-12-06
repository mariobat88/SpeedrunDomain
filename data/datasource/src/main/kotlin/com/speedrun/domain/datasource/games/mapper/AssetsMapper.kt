package com.speedrun.domain.datasource.games.mapper

import com.speedrun.domain.api.games.models.Assets
import com.speedrun.domain.data.database.entities.GameEntity
import com.speedrun.domain.repo.games.model.GameModel

fun Assets.toEntity() = GameEntity.Assets(
    logo = logo.uri,
    coverTiny = coverTiny.uri,
    coverSmall = coverSmall.uri,
    coverMedium = coverMedium.uri,
    coverLarge = coverLarge.uri,
    icon = icon.uri,
    trophy1st = trophy1st.uri,
    trophy2nd = trophy2nd.uri,
    trophy3rd = trophy3rd.uri,
    trophy4th = trophy4th.uri,
    background = background.uri,
    foreground = foreground.uri,
)

fun Assets.toModel() = GameModel.Assets(
    logo = logo.uri,
    coverTiny = coverTiny.uri,
    coverSmall = coverSmall.uri,
    coverMedium = coverMedium.uri,
    coverLarge = coverLarge.uri,
    icon = icon.uri,
    trophy1st = trophy1st.uri,
    trophy2nd = trophy2nd.uri,
    trophy3rd = trophy3rd.uri,
    trophy4th = trophy4th.uri,
    background = background.uri,
    foreground = foreground.uri,
)
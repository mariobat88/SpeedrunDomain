package com.speedrun.domain.data.datasource.common.mapper

import com.speedrun.domain.data.repo.common.model.LinkModel
import com.speedrun.domain.networking.api.common.LinkResponse

fun LinkResponse.toModel() = LinkModel(
    rel = rel,
    uri = uri
)

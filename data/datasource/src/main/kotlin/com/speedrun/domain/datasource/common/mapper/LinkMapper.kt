package com.speedrun.domain.datasource.common.mapper

import com.speedrun.domain.api.common.LinkResponse
import com.speedrun.domain.repo.common.model.LinkModel

fun LinkResponse.toModel() = LinkModel(
    rel = rel,
    uri = uri
)

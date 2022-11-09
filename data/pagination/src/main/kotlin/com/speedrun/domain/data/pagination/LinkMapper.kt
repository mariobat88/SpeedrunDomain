package com.speedrun.domain.data.pagination

import com.speedrun.domain.data.repo.common.model.LinkModel
import com.speedrun.domain.networking.api.common.LinkResponse

internal fun LinkResponse.toModel() = LinkModel(
    rel = rel,
    uri = uri
)

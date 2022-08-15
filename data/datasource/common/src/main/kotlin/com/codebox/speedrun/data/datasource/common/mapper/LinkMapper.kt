package com.codebox.speedrun.data.datasource.common.mapper

import com.codebox.speedrun.data.repo.common.model.LinkModel
import com.codebox.speedrun.domain.networking.api.common.LinkResponse

fun LinkResponse.toModel() = LinkModel(
    rel = rel,
    uri = uri
)

package com.codebox.speedrun.domain.data.pagination

import com.codebox.speedrun.domain.data.datasource.common.mapper.toModel
import com.codebox.speedrun.domain.networking.api.pagination.PaginationResponse

fun PaginationResponse.Pagination.toModel() = PaginationModel.Pagination(
    offset = offset,
    max = max,
    size = size,
    links = links.map { it.toModel() }
)

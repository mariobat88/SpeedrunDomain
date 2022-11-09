package com.speedrun.domain.data.pagination

import com.speedrun.domain.networking.api.pagination.PaginationResponse

fun PaginationResponse.Pagination.toModel() = PaginationModel.Pagination(
    offset = offset,
    max = max,
    size = size,
    links = links.map { it.toModel() }
)

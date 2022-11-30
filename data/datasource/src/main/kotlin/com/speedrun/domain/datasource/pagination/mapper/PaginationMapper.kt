package com.speedrun.domain.datasource.pagination.mapper

import com.speedrun.domain.api.pagination.models.PaginationResponse
import com.speedrun.domain.datasource.common.mapper.toModel
import com.speedrun.domain.repo.pagination.model.PaginationModel

fun PaginationResponse.Pagination.toModel() = PaginationModel.Pagination(
    offset = offset,
    max = max,
    size = size,
    links = links.map { it.toModel() }
)

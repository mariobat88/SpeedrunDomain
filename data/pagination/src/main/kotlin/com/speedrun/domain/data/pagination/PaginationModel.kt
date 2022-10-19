package com.speedrun.domain.data.pagination

import com.speedrun.domain.data.repo.common.model.LinkModel

data class PaginationModel<T>(
    val data: List<T>,
    val pagination: Pagination
) {
    data class Pagination(
        val offset: Int,
        val max: Int,
        val size: Int,
        val links: List<LinkModel>
    )
}

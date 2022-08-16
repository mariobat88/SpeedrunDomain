package com.codebox.speedrun.domain.data.repo.categories.model

import com.codebox.speedrun.domain.data.repo.common.model.LinkModel

data class CategoryModel(
    val id: String,
    val links: List<LinkModel>,
    val miscellaneous: Boolean,
    val name: String,
    val players: Players,
    val rules: String?,
    val type: String,
    val weblink: String
) {
    data class Players(
        val type: String,
        val value: Int
    )
}

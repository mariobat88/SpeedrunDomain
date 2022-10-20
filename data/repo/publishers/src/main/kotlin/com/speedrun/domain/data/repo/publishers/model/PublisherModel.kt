package com.speedrun.domain.data.repo.publishers.model

import com.speedrun.domain.data.repo.common.model.LinkModel

data class PublisherModel(
    val id: String,
    val name: String,
    val links: List<LinkModel>
)

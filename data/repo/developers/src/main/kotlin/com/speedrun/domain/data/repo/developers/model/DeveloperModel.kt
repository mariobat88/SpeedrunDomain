package com.speedrun.domain.data.repo.developers.model

import com.speedrun.domain.data.repo.common.model.LinkModel

data class DeveloperModel(
    val id: String,
    val name: String,
    val links: List<LinkModel>
)

package com.speedrun.domain.data.datasource.publishers.mapper

import com.speedrun.domain.data.database.entities.PublisherEntity
import com.speedrun.domain.data.repo.publishers.model.PublisherModel
import com.speedrun.domain.network.api.publishers.PublisherResponse

fun PublisherResponse.Data.toPublisherEntity() = PublisherEntity(
    id = id,
    name = name,
)

fun PublisherEntity.toPublisherModel() = PublisherModel(
    id = id,
    name = name,
    links = emptyList()
)

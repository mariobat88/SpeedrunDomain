package com.speedrun.domain.datasource.publishers.mapper

import com.speedrun.domain.api.publishers.models.PublisherResponse
import com.speedrun.domain.data.database.entities.PublisherEntity
import com.speedrun.domain.repo.publishers.model.PublisherModel

fun PublisherResponse.Data.toPublisherEntity() = PublisherEntity(
    id = id,
    name = name,
)

fun PublisherEntity.toPublisherModel() = PublisherModel(
    id = id,
    name = name,
    links = emptyList()
)

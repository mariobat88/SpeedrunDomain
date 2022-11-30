package com.speedrun.domain.repo.publishers

import com.speedrun.domain.repo.publishers.model.PublisherModel

interface PublishersRepository {

    suspend fun getPublisher(id: String): PublisherModel
}

package com.speedrun.domain.datasource.publishers

import com.speedrun.domain.api.publishers.PublishersApiService
import com.speedrun.domain.core.wrapper.dispatchers.DispatcherProvider
import com.speedrun.domain.data.database.SpeedrunDatabase
import com.speedrun.domain.datasource.publishers.mapper.toPublisherEntity
import com.speedrun.domain.datasource.publishers.mapper.toPublisherModel
import com.speedrun.domain.repo.publishers.PublishersRepository
import com.speedrun.domain.repo.publishers.model.PublisherModel
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PublishersRepositoryImpl @Inject constructor(
    private val publishersApiService: PublishersApiService,
    private val dispatcherProvider: DispatcherProvider,
    speedrunDatabase: SpeedrunDatabase,
) : PublishersRepository {

    private val publisherDao = speedrunDatabase.publisherDao()

    override suspend fun getPublisher(id: String): PublisherModel =
        withContext(dispatcherProvider.io()) {
            val developerResponse = publishersApiService.getPublisher(id)
            val publisherEntity = developerResponse.data.toPublisherEntity()

            publisherDao.upsert(publisherEntity)
            publisherEntity.toPublisherModel()
        }
}

package com.speedrun.domain.datasource.developers

import com.speedrun.domain.api.developers.DevelopersApiService
import com.speedrun.domain.core.wrapper.dispatchers.DispatcherProvider
import com.speedrun.domain.data.database.SpeedrunDatabase
import com.speedrun.domain.datasource.developers.mapper.toDeveloperEntity
import com.speedrun.domain.datasource.developers.mapper.toDeveloperModel
import com.speedrun.domain.repo.developers.DevelopersRepository
import com.speedrun.domain.repo.developers.model.DeveloperModel
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DevelopersRepositoryImpl @Inject constructor(
    private val developersApiService: DevelopersApiService,
    private val dispatcherProvider: DispatcherProvider,
    speedrunDatabase: SpeedrunDatabase,
) : DevelopersRepository {

    private val developerDao = speedrunDatabase.developerDao()

    override suspend fun getDeveloper(id: String): DeveloperModel =
        withContext(dispatcherProvider.io()) {
            val developerResponse = developersApiService.getDeveloper(id)
            val developerEntity = developerResponse.data.toDeveloperEntity()

            developerDao.upsert(developerEntity)
            developerEntity.toDeveloperModel()
        }
}

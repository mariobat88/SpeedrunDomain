package com.speedrun.domain.datasource.categories

import com.speedrun.domain.api.categories.CategoriesApiService
import com.speedrun.domain.core.wrapper.dispatchers.DispatcherProvider
import com.speedrun.domain.data.database.SpeedrunDatabase
import com.speedrun.domain.datasource.categories.mapper.toEntity
import com.speedrun.domain.datasource.categories.mapper.toModel
import com.speedrun.domain.repo.categories.CategoriesRepository
import com.speedrun.domain.repo.categories.model.CategoryModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoriesRepositoryImpl @Inject constructor(
    private val categoriesApiService: CategoriesApiService,
    private val dispatcherProvider: DispatcherProvider,
    speedrunDatabase: SpeedrunDatabase,
) : CategoriesRepository {

    private val categoryDao = speedrunDatabase.categoryDao()

    override suspend fun refreshCategoriesByGame(gameId: String) =
        withContext(dispatcherProvider.io()) {
            val response = categoriesApiService.getCategoriesByGame(gameId)
            val categoryEntities = response.data.map { it.toEntity(gameId) }

            categoryDao.upsert(categoryEntities)
        }

    override suspend fun observeCategoriesByGame(gameId: String): Flow<List<CategoryModel>> =
        withContext(dispatcherProvider.io()) {
            categoryDao.observeCategoriesByGameId(gameId).map { it.map { it.toModel() } }
        }
}

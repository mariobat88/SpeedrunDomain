package com.speedrun.domain.data.datasource.categories

import com.speedrun.domain.core.wrapper.dispatchers.DispatcherProvider
import com.speedrun.domain.data.datasource.categories.mapper.toModel
import com.speedrun.domain.data.repo.categories.CategoriesRepository
import com.speedrun.domain.data.repo.categories.model.CategoryModel
import com.speedrun.domain.networking.api.categories.CategoriesApiService
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoriesRepositoryImpl @Inject constructor(
    private val categoriesApiService: CategoriesApiService,
    private val dispatcherProvider: DispatcherProvider
) : CategoriesRepository {

    override suspend fun getCategoriesByGame(gameId: String): List<CategoryModel> =
        withContext(dispatcherProvider.io()) {
            val response = categoriesApiService.getCategoriesByGame(gameId)
            response.data.map { it.toModel() }
        }
}
package com.speedrun.domain.repo.categories

import com.speedrun.domain.repo.categories.model.CategoryModel
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {

    suspend fun refreshCategoriesByGame(gameId: String)
    suspend fun observeCategoriesByGame(gameId: String): Flow<List<CategoryModel>>
}

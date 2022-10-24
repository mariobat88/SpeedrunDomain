package com.speedrun.domain.data.repo.categories

import com.speedrun.domain.data.repo.categories.model.CategoryModel

interface CategoriesRepository {

    suspend fun getCategoriesByGame(gameId: String): List<CategoryModel>
}

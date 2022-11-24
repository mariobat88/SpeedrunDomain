package com.speedrun.domain.data.database.daos

import androidx.room.Dao
import com.speedrun.domain.data.database.entities.CategoryEntity

@Dao
abstract class CategoryDao : BaseDao<CategoryEntity>()

package com.speedrun.domain.data.database.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.speedrun.domain.data.database.entities.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class CategoryDao : BaseDao<CategoryEntity>() {

    @Transaction
    @Query(
        """
        SELECT * FROM ${CategoryEntity.TABLE_NAME} WHERE ${CategoryEntity.COLUMN_GAME_ID} = :gameId
        """
    )
    abstract fun observeCategoriesByGameId(gameId: String): Flow<List<CategoryEntity>>

}

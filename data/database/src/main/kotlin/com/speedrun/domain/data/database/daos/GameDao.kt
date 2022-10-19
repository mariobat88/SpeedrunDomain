package com.speedrun.domain.data.database.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.speedrun.domain.data.database.entities.GameEntity
import com.speedrun.domain.data.database.result.GameEntityResult
import kotlinx.coroutines.flow.Flow

@Dao
abstract class GameDao : BaseDao<GameEntity>() {

    @Transaction
    @Query("SELECT * FROM ${GameEntity.TABLE_NAME} WHERE ${GameEntity.COLUMN_ID} = :id")
    abstract fun getGameById(id: String): Flow<GameEntityResult>
}

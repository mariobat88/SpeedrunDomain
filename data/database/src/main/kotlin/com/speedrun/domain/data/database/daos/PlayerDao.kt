package com.speedrun.domain.data.database.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.speedrun.domain.data.database.entities.PlayerEntity
import com.speedrun.domain.data.database.result.PlayerResult
import kotlinx.coroutines.flow.Flow

@Dao
abstract class PlayerDao : BaseDao<PlayerEntity>() {

    @Transaction
    @Query("SELECT * FROM ${PlayerEntity.TABLE_NAME} WHERE ${PlayerEntity.COLUMN_ID} = :playerId")
    abstract fun observePlayer(playerId: String): Flow<PlayerResult>
}

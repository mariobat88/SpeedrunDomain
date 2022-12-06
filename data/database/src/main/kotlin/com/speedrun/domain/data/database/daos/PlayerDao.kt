package com.speedrun.domain.data.database.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.speedrun.domain.data.database.entities.PlaceEntity
import com.speedrun.domain.data.database.entities.PlayerEntity
import com.speedrun.domain.data.database.entities.RunPlayerEntity
import com.speedrun.domain.data.database.result.PlayerResult
import com.speedrun.domain.data.database.result.RunPlaceResult
import kotlinx.coroutines.flow.Flow

@Dao
abstract class PlayerDao : BaseDao<PlayerEntity>() {

    @Transaction
    @Query("SELECT * FROM ${PlayerEntity.TABLE_NAME} WHERE ${PlayerEntity.COLUMN_ID} = :playerId")
    abstract fun observePlayer(playerId: String): Flow<PlayerResult>

    @Transaction
    @Query(
        """
        SELECT * FROM ${PlayerEntity.TABLE_NAME} 
        LEFT JOIN ${RunPlayerEntity.TABLE_NAME} ON ${PlayerEntity.COLUMN_ID} =  ${RunPlayerEntity.COLUMN_PLAYER_ID}
        LEFT JOIN ${PlaceEntity.TABLE_NAME} ON ${RunPlayerEntity.COLUMN_RUN_ID} =  ${PlaceEntity.COLUMN_RUN_ID}
        WHERE ${PlayerEntity.COLUMN_ID} = :playerId 
        """
    )
    abstract fun observePersonalBests(playerId: String): Flow<List<RunPlaceResult>>
}

package com.speedrun.domain.data.database.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.speedrun.domain.data.database.entities.LeaderboardEntity
import com.speedrun.domain.data.database.entities.LeaderboardRunEntity
import com.speedrun.domain.data.database.result.LeaderboardResult
import com.speedrun.domain.data.database.result.LeaderboardRunResult
import kotlinx.coroutines.flow.Flow

@Dao
abstract class LeaderboardDao : BaseDao<LeaderboardEntity>() {

    @Transaction
    @Query("SELECT * FROM ${LeaderboardEntity.TABLE_NAME} WHERE ${LeaderboardEntity.COLUMN_GAME_ID} = :gameId AND ${LeaderboardEntity.COLUMN_CATEGORY_ID} = :categoryId")
    abstract fun getLeaderboard(
        gameId: String,
        categoryId: String
    ): Flow<LeaderboardResult>

    @Transaction
    @Query("SELECT * FROM ${LeaderboardRunEntity.TABLE_NAME} WHERE ${LeaderboardRunEntity.COLUMN_RUN_ID} = :runId")
    abstract fun getLeaderboardPlace(runId: String): Flow<LeaderboardRunResult>
}

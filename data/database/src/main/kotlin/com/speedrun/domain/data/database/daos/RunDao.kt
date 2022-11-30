package com.speedrun.domain.data.database.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.speedrun.domain.data.database.entities.LeaderboardRunEntity
import com.speedrun.domain.data.database.entities.RunEntity
import com.speedrun.domain.data.database.result.LeaderboardRunResult
import com.speedrun.domain.data.database.result.RunResult
import kotlinx.coroutines.flow.Flow

@Dao
abstract class RunDao : BaseDao<RunEntity>(){

    @Transaction
    @Query("SELECT * FROM ${RunEntity.TABLE_NAME} WHERE ${RunEntity.COLUMN_ID} = :runId")
    abstract fun observeRun(runId: String): Flow<RunResult>

    @Transaction
    @Query("SELECT * FROM ${LeaderboardRunEntity.TABLE_NAME} WHERE ${LeaderboardRunEntity.COLUMN_LEADERBOARD_ID} = :leaderboardId")
    abstract fun getLeaderboardPlace(
        leaderboardId: String,
    ): Flow<LeaderboardRunResult>
}

package com.speedrun.domain.data.database.daos

import androidx.room.Dao
import com.speedrun.domain.data.database.entities.LeaderboardEntity

@Dao
abstract class LeaderboardDao : BaseDao<LeaderboardEntity>()

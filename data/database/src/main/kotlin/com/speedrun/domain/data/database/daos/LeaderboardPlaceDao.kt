package com.speedrun.domain.data.database.daos

import androidx.room.Dao
import com.speedrun.domain.data.database.entities.LeaderboardPlaceEntity

@Dao
abstract class LeaderboardPlaceDao : BaseDao<LeaderboardPlaceEntity>()

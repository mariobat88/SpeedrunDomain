package com.speedrun.domain.data.database.daos

import androidx.room.Dao
import com.speedrun.domain.data.database.entities.LeaderboardRunEntity

@Dao
abstract class LeaderboardPlaceDao : BaseDao<LeaderboardRunEntity>()

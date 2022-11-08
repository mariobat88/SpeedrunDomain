package com.speedrun.domain.data.database.daos

import androidx.room.Dao
import com.speedrun.domain.data.database.entities.RegionEntity

@Dao
abstract class RegionDao : BaseDao<RegionEntity>()

package com.speedrun.domain.data.database.daos

import androidx.room.Dao
import com.speedrun.domain.data.database.entities.PlaceEntity

@Dao
abstract class PlaceDao : BaseDao<PlaceEntity>()

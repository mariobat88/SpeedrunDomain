package com.speedrun.domain.data.database.daos

import androidx.room.Dao
import com.speedrun.domain.data.database.entities.DeveloperEntity

@Dao
abstract class DeveloperDao : BaseDao<DeveloperEntity>()

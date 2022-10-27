package com.speedrun.domain.data.database.daos

import androidx.room.Dao
import com.speedrun.domain.data.database.entities.RunEntity

@Dao
abstract class RunDao : BaseDao<RunEntity>()

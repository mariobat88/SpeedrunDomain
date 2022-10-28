package com.speedrun.domain.data.database.daos

import androidx.room.Dao
import com.speedrun.domain.data.database.entities.RunPlayerEntity

@Dao
abstract class RunPlayerDao : BaseDao<RunPlayerEntity>()

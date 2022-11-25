package com.speedrun.domain.data.database.daos

import androidx.room.Dao
import com.speedrun.domain.data.database.entities.RunValueEntity

@Dao
abstract class RunValueDao : BaseDao<RunValueEntity>()

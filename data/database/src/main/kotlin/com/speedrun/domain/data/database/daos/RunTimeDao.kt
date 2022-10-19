package com.speedrun.domain.data.database.daos

import androidx.room.Dao
import com.speedrun.domain.data.database.entities.RunTimeEntity

@Dao
abstract class RunTimeDao : BaseDao<RunTimeEntity>()

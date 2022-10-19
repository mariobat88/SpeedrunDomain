package com.codebox.speedrun.domain.data.database.daos

import androidx.room.Dao
import com.codebox.speedrun.domain.data.database.entities.RunTimeEntity

@Dao
abstract class RunTimeDao : BaseDao<RunTimeEntity>()

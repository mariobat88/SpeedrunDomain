package com.speedrun.domain.data.database.daos

import androidx.room.Dao
import com.speedrun.domain.data.database.entities.VariableEntity

@Dao
abstract class VariableDao : BaseDao<VariableEntity>()

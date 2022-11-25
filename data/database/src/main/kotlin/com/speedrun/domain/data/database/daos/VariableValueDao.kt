package com.speedrun.domain.data.database.daos

import androidx.room.Dao
import com.speedrun.domain.data.database.entities.VariableValueEntity

@Dao
abstract class VariableValueDao : BaseDao<VariableValueEntity>()

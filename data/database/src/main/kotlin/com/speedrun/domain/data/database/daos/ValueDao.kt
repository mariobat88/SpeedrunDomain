package com.speedrun.domain.data.database.daos

import androidx.room.Dao
import com.speedrun.domain.data.database.entities.ValueEntity

@Dao
abstract class ValueDao : BaseDao<ValueEntity>()

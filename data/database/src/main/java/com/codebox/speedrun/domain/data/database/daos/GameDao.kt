package com.codebox.speedrun.domain.data.database.daos

import androidx.room.Dao
import com.codebox.speedrun.domain.data.database.entities.GameEntity

@Dao
abstract class GameDao : BaseDao<GameEntity>()

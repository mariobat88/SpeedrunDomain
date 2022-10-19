package com.codebox.speedrun.domain.data.database.daos

import androidx.room.Dao
import com.codebox.speedrun.domain.data.database.entities.GameRunTimeEntity

@Dao
abstract class GameRunTimeDao : BaseDao<GameRunTimeEntity>()

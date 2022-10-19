package com.speedrun.domain.data.database.daos

import androidx.room.Dao
import com.speedrun.domain.data.database.entities.GameRunTimeEntity

@Dao
abstract class GameRunTimeDao : BaseDao<GameRunTimeEntity>()

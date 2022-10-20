package com.speedrun.domain.data.database.daos

import androidx.room.Dao
import com.speedrun.domain.data.database.entities.GameDeveloperEntity

@Dao
abstract class GameDeveloperDao : BaseDao<GameDeveloperEntity>()

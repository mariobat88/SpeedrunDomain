package com.speedrun.domain.data.database.daos

import androidx.room.Dao
import com.speedrun.domain.data.database.entities.PlatformEntity

@Dao
abstract class PlatformDao : BaseDao<PlatformEntity>()

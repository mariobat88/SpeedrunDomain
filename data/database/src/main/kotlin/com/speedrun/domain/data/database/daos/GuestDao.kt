package com.speedrun.domain.data.database.daos

import androidx.room.Dao
import com.speedrun.domain.data.database.entities.GuestEntity

@Dao
abstract class GuestDao : BaseDao<GuestEntity>()

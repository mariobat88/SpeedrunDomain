package com.speedrun.domain.data.database.daos

import androidx.room.Dao
import com.speedrun.domain.data.database.entities.UserLocationEntity

@Dao
abstract class UserLocationDao : BaseDao<UserLocationEntity>()

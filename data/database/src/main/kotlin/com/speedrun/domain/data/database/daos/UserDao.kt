package com.speedrun.domain.data.database.daos

import androidx.room.Dao
import com.speedrun.domain.data.database.entities.GameRunTimeEntity
import com.speedrun.domain.data.database.entities.UserEntity

@Dao
abstract class UserDao : BaseDao<UserEntity>()

package com.codebox.speedrun.domain.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codebox.speedrun.domain.data.database.daos.FollowedGameDao
import com.codebox.speedrun.domain.data.database.daos.GameDao
import com.codebox.speedrun.domain.data.database.entities.FollowedGameEntity
import com.codebox.speedrun.domain.data.database.entities.GameEntity

@Database(
    entities = [
        FollowedGameEntity::class,
        GameEntity::class
    ],
    version = 1
)
abstract class SpeedrunDatabase : RoomDatabase() {
    abstract fun followedGameDao(): FollowedGameDao
    abstract fun gameDao(): GameDao
}

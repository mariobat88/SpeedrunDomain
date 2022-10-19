package com.codebox.speedrun.domain.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codebox.speedrun.domain.data.database.daos.FollowedGameDao
import com.codebox.speedrun.domain.data.database.daos.GameDao
import com.codebox.speedrun.domain.data.database.daos.GameRunTimeDao
import com.codebox.speedrun.domain.data.database.daos.RunTimeDao
import com.codebox.speedrun.domain.data.database.entities.FollowedGameEntity
import com.codebox.speedrun.domain.data.database.entities.GameEntity
import com.codebox.speedrun.domain.data.database.entities.GameRunTimeEntity
import com.codebox.speedrun.domain.data.database.entities.RunTimeEntity

@Database(
    entities = [
        FollowedGameEntity::class,
        GameEntity::class,
        RunTimeEntity::class,
        GameRunTimeEntity::class,
    ],
    version = 1
)
abstract class SpeedrunDatabase : RoomDatabase() {
    abstract fun followedGameDao(): FollowedGameDao
    abstract fun gameDao(): GameDao
    abstract fun runTimeDao(): RunTimeDao
    abstract fun gameRunTimeDao(): GameRunTimeDao
}

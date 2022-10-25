package com.speedrun.domain.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.speedrun.domain.data.database.daos.*
import com.speedrun.domain.data.database.entities.*

@Database(
    entities = [
        DeveloperEntity::class,
        PublisherEntity::class,
        GameEntity::class,
        RunTimeEntity::class,
        GameRunTimeEntity::class,
        GameDeveloperEntity::class,
        GamePublisherEntity::class,
        PlayerEntity::class,
        UserEntity::class,
        GuestEntity::class,
    ],
    version = 1
)
abstract class SpeedrunDatabase : RoomDatabase() {
    abstract fun developerDao(): DeveloperDao
    abstract fun publisherDao(): PublisherDao
    abstract fun gameDao(): GameDao
    abstract fun runTimeDao(): RunTimeDao
    abstract fun gameRunTimeDao(): GameRunTimeDao
    abstract fun gameDeveloperDao(): GameDeveloperDao
    abstract fun playerDao(): PlayerDao
    abstract fun userDao(): UserDao
    abstract fun guestDao(): GuestDao
}

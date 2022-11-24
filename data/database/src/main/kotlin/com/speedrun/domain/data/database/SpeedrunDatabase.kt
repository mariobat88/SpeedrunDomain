package com.speedrun.domain.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.speedrun.domain.data.database.daos.*
import com.speedrun.domain.data.database.entities.*

@Database(
    entities = [
        RunEntity::class,
        RunPlayerEntity::class,
        DeveloperEntity::class,
        PublisherEntity::class,
        PlatformEntity::class,
        GameEntity::class,
        RunTimeEntity::class,
        GameRunTimeEntity::class,
        GameDeveloperEntity::class,
        GamePublisherEntity::class,
        PlayerEntity::class,
        UserEntity::class,
        GuestEntity::class,
        LocationEntity::class,
        UserLocationEntity::class,
        LeaderboardEntity::class,
        LeaderboardPlaceEntity::class,
        VideoEntity::class,
        CategoryEntity::class,
    ],
    version = 1
)
abstract class SpeedrunDatabase : RoomDatabase() {
    abstract fun runDao(): RunDao
    abstract fun runPlayerDao(): RunPlayerDao
    abstract fun videoDao(): VideoDao
    abstract fun developerDao(): DeveloperDao
    abstract fun publisherDao(): PublisherDao
    abstract fun platformDao(): PlatformDao
    abstract fun gameDao(): GameDao
    abstract fun runTimeDao(): RunTimeDao
    abstract fun gameRunTimeDao(): GameRunTimeDao
    abstract fun gameDeveloperDao(): GameDeveloperDao
    abstract fun playerDao(): PlayerDao
    abstract fun userDao(): UserDao
    abstract fun guestDao(): GuestDao
    abstract fun locationDao(): LocationDao
    abstract fun userLocationDao(): UserLocationDao
    abstract fun leaderboardDao(): LeaderboardDao
    abstract fun leaderboardPlaceDao(): LeaderboardPlaceDao
    abstract fun categoryDao(): CategoryDao
}

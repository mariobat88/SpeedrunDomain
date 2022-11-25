package com.speedrun.domain.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.speedrun.domain.data.database.daos.*
import com.speedrun.domain.data.database.entities.*

@Database(
    entities = [
        CategoryEntity::class,
        DeveloperEntity::class,
        GameDeveloperEntity::class,
        GameEntity::class,
        GamePublisherEntity::class,
        GameRunTimeEntity::class,
        GuestEntity::class,
        LeaderboardEntity::class,
        LeaderboardPlaceEntity::class,
        LocationEntity::class,
        PlatformEntity::class,
        PlayerEntity::class,
        PublisherEntity::class,
        RunEntity::class,
        RunPlayerEntity::class,
        RunTimeEntity::class,
        RunValueEntity::class,
        UserEntity::class,
        UserLocationEntity::class,
        ValueEntity::class,
        VariableEntity::class,
        VariableValueEntity::class,
        VideoEntity::class,
    ],
    version = 1
)
abstract class SpeedrunDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun developerDao(): DeveloperDao
    abstract fun gameDao(): GameDao
    abstract fun gameDeveloperDao(): GameDeveloperDao
    abstract fun gameRunTimeDao(): GameRunTimeDao
    abstract fun guestDao(): GuestDao
    abstract fun leaderboardDao(): LeaderboardDao
    abstract fun leaderboardPlaceDao(): LeaderboardPlaceDao
    abstract fun locationDao(): LocationDao
    abstract fun platformDao(): PlatformDao
    abstract fun playerDao(): PlayerDao
    abstract fun publisherDao(): PublisherDao
    abstract fun runDao(): RunDao
    abstract fun runPlayerDao(): RunPlayerDao
    abstract fun runTimeDao(): RunTimeDao
    abstract fun runValueDao(): RunValueDao
    abstract fun userDao(): UserDao
    abstract fun userLocationDao(): UserLocationDao
    abstract fun valueDao(): ValueDao
    abstract fun variableDao(): VariableDao
    abstract fun variableValueDao(): VariableValueDao
    abstract fun videoDao(): VideoDao
}

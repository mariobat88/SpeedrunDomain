package com.speedrun.domain.data.database.result

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.speedrun.domain.data.database.entities.*

data class GameResult(
    @Embedded
    val gameEntity: GameEntity,
    @Relation(
        entity = RunTimeEntity::class,
        entityColumn = RunTimeEntity.COLUMN_ID,
        parentColumn = GameEntity.COLUMN_ID,
        associateBy = Junction(
            value = GameRunTimeEntity::class,
            parentColumn = GameRunTimeEntity.COLUMN_GAME_ID,
            entityColumn = GameRunTimeEntity.COLUMN_RUN_TIME_ID,
        )
    )
    val runTimes: List<RunTimeEntity>?,
    @Relation(
        entity = GameDeveloperEntity::class,
        entityColumn = GameEntity.COLUMN_ID,
        parentColumn = GameEntity.COLUMN_ID,
    )
    val developers: List<GameDeveloperEntity>?,
    @Relation(
        entity = GamePublisherEntity::class,
        entityColumn = GameEntity.COLUMN_ID,
        parentColumn = GameEntity.COLUMN_ID,
    )
    val publishers: List<GamePublisherEntity>?
)

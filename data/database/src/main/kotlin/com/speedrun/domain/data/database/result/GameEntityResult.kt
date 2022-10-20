package com.speedrun.domain.data.database.result

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.speedrun.domain.data.database.entities.*

data class GameEntityResult(
    @Embedded
    val gameEntity: GameEntity,
    @Relation(
        entity = RunTimeEntity::class,
        entityColumn = RunTimeEntity.COLUMN_ID,
        parentColumn = GameEntity.COLUMN_ID,
        associateBy = Junction(GameRunTimeEntity::class)
    )
    val runTimes: List<RunTimeEntity>?,
    @Relation(
        entity = GameDeveloperEntity::class,
        entityColumn = GameEntity.COLUMN_ID,
        parentColumn = GameEntity.COLUMN_ID,
    )
    val developers: List<GameDeveloperEntity>?
)

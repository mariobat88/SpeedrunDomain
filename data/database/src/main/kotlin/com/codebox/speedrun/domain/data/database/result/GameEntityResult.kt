package com.codebox.speedrun.domain.data.database.result

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.codebox.speedrun.domain.data.database.entities.GameEntity
import com.codebox.speedrun.domain.data.database.entities.GameRunTimeEntity
import com.codebox.speedrun.domain.data.database.entities.RunTimeEntity

data class GameEntityResult(
    @Embedded
    val gameEntity: GameEntity,
    @Relation(
        entity = RunTimeEntity::class,
        entityColumn = RunTimeEntity.COLUMN_ID,
        parentColumn = GameEntity.COLUMN_ID,
        associateBy = Junction(GameRunTimeEntity::class)
    )
    val runTimes: List<RunTimeEntity>?
)

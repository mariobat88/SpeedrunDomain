package com.speedrun.domain.data.database.result

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.speedrun.domain.data.database.entities.*

data class LeaderboardEntityResult(
    @Embedded
    val leaderboardEntity: LeaderboardEntity,
    @Relation(
        entity = GameEntity::class,
        entityColumn = GameEntity.COLUMN_ID,
        parentColumn = LeaderboardEntity.COLUMN_GAME,
    )
    val gameEntity: GameEntity?,
    @Relation(
        entity = RunEntity::class,
        entityColumn = RunEntity.COLUMN_ID,
        parentColumn = LeaderboardEntity.COLUMN_ID,
        associateBy = Junction(LeaderboardPlaceEntity::class)
    )
    val runs: List<RunEntity>?,
)

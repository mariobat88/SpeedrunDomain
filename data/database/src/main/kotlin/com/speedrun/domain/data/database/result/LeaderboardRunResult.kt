package com.speedrun.domain.data.database.result

import androidx.room.Embedded
import androidx.room.Relation
import com.speedrun.domain.data.database.entities.LeaderboardRunEntity
import com.speedrun.domain.data.database.entities.PlaceEntity
import com.speedrun.domain.data.database.entities.RunEntity

data class LeaderboardRunResult(
    @Embedded
    val leaderboardRunEntity: LeaderboardRunEntity,
    @Relation(
        entity = RunEntity::class,
        entityColumn = RunEntity.COLUMN_ID,
        parentColumn = LeaderboardRunEntity.COLUMN_RUN_ID,
    )
    val runResult: RunResult?,
    @Relation(
        entity = PlaceEntity::class,
        entityColumn = PlaceEntity.COLUMN_RUN_ID,
        parentColumn = LeaderboardRunEntity.COLUMN_RUN_ID,
    )
    val placeEntity: PlaceEntity?,
)

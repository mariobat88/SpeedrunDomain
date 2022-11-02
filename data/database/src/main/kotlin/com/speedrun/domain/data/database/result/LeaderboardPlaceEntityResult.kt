package com.speedrun.domain.data.database.result

import androidx.room.Embedded
import androidx.room.Relation
import com.speedrun.domain.data.database.entities.LeaderboardPlaceEntity
import com.speedrun.domain.data.database.entities.RunEntity

data class LeaderboardPlaceEntityResult(
    @Embedded
    val leaderboardPlaceEntity: LeaderboardPlaceEntity,
    @Relation(
        entity = RunEntity::class,
        entityColumn = RunEntity.COLUMN_ID,
        parentColumn = LeaderboardPlaceEntity.COLUMN_RUN_ID,
    )
    val runs: RunEntityResult?,
)

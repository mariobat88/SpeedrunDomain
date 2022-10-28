package com.speedrun.domain.data.database.result

import androidx.room.Embedded
import androidx.room.Relation
import com.speedrun.domain.data.database.entities.GameEntity
import com.speedrun.domain.data.database.entities.LeaderboardEntity
import com.speedrun.domain.data.database.entities.LeaderboardPlaceEntity

data class LeaderboardEntityResult(
    @Embedded
    val leaderboardEntity: LeaderboardEntity,
    @Relation(
        entity = LeaderboardPlaceEntity::class,
        entityColumn = LeaderboardPlaceEntity.COLUMN_LEADERBOARD_ID,
        parentColumn = LeaderboardEntity.COLUMN_ID,
    )
    val leaderboardPlaces: List<LeaderboardPlaceEntityResult> = emptyList(),
)

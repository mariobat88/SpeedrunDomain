package com.speedrun.domain.data.database.result

import androidx.room.Embedded
import androidx.room.Relation
import com.speedrun.domain.data.database.entities.CategoryEntity
import com.speedrun.domain.data.database.entities.LeaderboardEntity
import com.speedrun.domain.data.database.entities.LeaderboardPlaceEntity

data class LeaderboardResult(
    @Embedded
    val leaderboardEntity: LeaderboardEntity,
    @Relation(
        entity = LeaderboardPlaceEntity::class,
        entityColumn = LeaderboardPlaceEntity.COLUMN_LEADERBOARD_ID,
        parentColumn = LeaderboardEntity.COLUMN_ID,
    )
    val leaderboardPlaces: List<LeaderboardPlaceResult> = emptyList(),
    @Relation(
        entity = CategoryEntity::class,
        entityColumn = CategoryEntity.COLUMN_ID,
        parentColumn = LeaderboardEntity.COLUMN_CATEGORY_ID,
    )
    val categoryResult: CategoryResult?,
)

package com.speedrun.domain.data.database.result

import androidx.room.Embedded
import androidx.room.Relation
import com.speedrun.domain.data.database.entities.CategoryEntity
import com.speedrun.domain.data.database.entities.LeaderboardEntity
import com.speedrun.domain.data.database.entities.LeaderboardRunEntity

data class LeaderboardResult(
    @Embedded
    val leaderboardEntity: LeaderboardEntity,
    @Relation(
        entity = LeaderboardRunEntity::class,
        entityColumn = LeaderboardRunEntity.COLUMN_LEADERBOARD_ID,
        parentColumn = LeaderboardEntity.COLUMN_ID,
    )
    val leaderboardPlaces: List<LeaderboardRunResult> = emptyList(),
    @Relation(
        entity = CategoryEntity::class,
        entityColumn = CategoryEntity.COLUMN_ID,
        parentColumn = LeaderboardEntity.COLUMN_CATEGORY_ID,
    )
    val categoryResult: CategoryResult?,
)

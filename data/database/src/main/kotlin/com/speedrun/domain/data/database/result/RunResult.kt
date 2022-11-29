package com.speedrun.domain.data.database.result

import androidx.room.Embedded
import androidx.room.Relation
import com.speedrun.domain.data.database.entities.*

data class RunResult(
    @Embedded
    val run: RunEntity,
    @Relation(
        entity = GameEntity::class,
        entityColumn = GameEntity.COLUMN_ID,
        parentColumn = RunEntity.COLUMN_GAME_ID,
    )
    val game: GameEntity?,
    @Relation(
        entity = CategoryEntity::class,
        entityColumn = CategoryEntity.COLUMN_ID,
        parentColumn = RunEntity.COLUMN_CATEGORY_ID,
    )
    val categoryResult: CategoryResult?,
    @Relation(
        entity = PlatformEntity::class,
        entityColumn = PlatformEntity.COLUMN_ID,
        parentColumn = RunEntity.System.COLUMN_PLATFORM,
    )
    val platform: PlatformEntity?,
    @Relation(
        entity = RunPlayerEntity::class,
        entityColumn = RunPlayerEntity.COLUMN_RUN_ID,
        parentColumn = RunEntity.COLUMN_ID,
    )
    val runPlayers: List<RunPlayerResult> = emptyList(),
    @Relation(
        entity = VideoEntity::class,
        entityColumn = VideoEntity.COLUMN_RUN_ID,
        parentColumn = RunEntity.COLUMN_ID,
    )
    val videos: List<VideoEntity> = emptyList(),
    @Relation(
        entity = RunValueEntity::class,
        entityColumn = RunValueEntity.COLUMN_RUN_ID,
        parentColumn = RunEntity.COLUMN_ID,
    )
    val runValueEntities: List<RunValueEntity> = emptyList()
)

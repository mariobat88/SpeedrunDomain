package com.speedrun.domain.data.database.result

import androidx.room.Embedded
import androidx.room.Relation
import com.speedrun.domain.data.database.entities.GameEntity
import com.speedrun.domain.data.database.entities.PlatformEntity
import com.speedrun.domain.data.database.entities.RunEntity
import com.speedrun.domain.data.database.entities.RunPlayerEntity

data class RunResult(
    @Embedded
    val run: RunEntity,
    @Relation(
        entity = GameEntity::class,
        entityColumn = GameEntity.COLUMN_ID,
        parentColumn = RunEntity.COLUMN_GAME,
    )
    val game: GameEntity?,
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
)

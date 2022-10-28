package com.speedrun.domain.data.database.result

import androidx.room.Embedded
import androidx.room.Relation
import com.speedrun.domain.data.database.entities.RunEntity
import com.speedrun.domain.data.database.entities.RunPlayerEntity

data class RunEntityResult(
    @Embedded
    val runEntity: RunEntity,
    @Relation(
        entity = RunPlayerEntity::class,
        entityColumn = RunPlayerEntity.COLUMN_RUN_ID,
        parentColumn = RunEntity.COLUMN_ID,
    )
    val players: List<RunPlayerEntity> = emptyList(),
)

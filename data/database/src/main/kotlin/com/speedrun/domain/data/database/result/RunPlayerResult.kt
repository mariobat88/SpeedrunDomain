package com.speedrun.domain.data.database.result

import androidx.room.Embedded
import androidx.room.Relation
import com.speedrun.domain.data.database.entities.PlayerEntity
import com.speedrun.domain.data.database.entities.RunPlayerEntity

data class RunPlayerResult(
    @Embedded
    val runPlayer: RunPlayerEntity,
    @Relation(
        entity = PlayerEntity::class,
        entityColumn = PlayerEntity.COLUMN_ID,
        parentColumn = RunPlayerEntity.COLUMN_PLAYER_ID,
    )
    val players: List<PlayerResult>
)

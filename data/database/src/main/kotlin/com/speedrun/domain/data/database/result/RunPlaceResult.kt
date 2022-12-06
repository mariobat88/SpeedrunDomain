package com.speedrun.domain.data.database.result

import androidx.room.Embedded
import androidx.room.Relation
import com.speedrun.domain.data.database.entities.PlaceEntity
import com.speedrun.domain.data.database.entities.RunEntity

data class RunPlaceResult(
    @Embedded
    val placeEntity: PlaceEntity,
    @Relation(
        entity = RunEntity::class,
        entityColumn = RunEntity.COLUMN_ID,
        parentColumn = PlaceEntity.COLUMN_RUN_ID,
    )
    val runResult: RunResult,
)

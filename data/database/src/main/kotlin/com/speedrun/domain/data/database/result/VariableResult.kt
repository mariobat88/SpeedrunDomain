package com.speedrun.domain.data.database.result

import androidx.room.Embedded
import androidx.room.Relation
import com.speedrun.domain.data.database.entities.VariableEntity
import com.speedrun.domain.data.database.entities.VariableValueEntity

data class VariableResult(
    @Embedded
    val variableEntity: VariableEntity,
    @Relation(
        entity = VariableValueEntity::class,
        entityColumn = VariableValueEntity.COLUMN_VARIABLE_ID,
        parentColumn = VariableEntity.COLUMN_ID,
    )
    val valueEntities: List<VariableValueEntity> = emptyList()
)

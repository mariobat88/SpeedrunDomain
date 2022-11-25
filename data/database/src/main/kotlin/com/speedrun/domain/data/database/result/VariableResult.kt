package com.speedrun.domain.data.database.result

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.speedrun.domain.data.database.entities.ValueEntity
import com.speedrun.domain.data.database.entities.VariableEntity
import com.speedrun.domain.data.database.entities.VariableValueEntity

data class VariableResult(
    @Embedded
    val variableEntity: VariableEntity,
    @Relation(
        entity = ValueEntity::class,
        entityColumn = ValueEntity.COLUMN_ID,
        parentColumn = VariableEntity.COLUMN_ID,
        associateBy = Junction(VariableValueEntity::class)
    )
    val valueEntities: List<ValueEntity> = emptyList()
)

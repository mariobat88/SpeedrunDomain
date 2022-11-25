package com.speedrun.domain.data.database.result

import androidx.room.Embedded
import androidx.room.Relation
import com.speedrun.domain.data.database.entities.CategoryEntity
import com.speedrun.domain.data.database.entities.VariableEntity

data class CategoryResult(
    @Embedded
    val categoryEntity: CategoryEntity?,
    @Relation(
        entity = VariableEntity::class,
        entityColumn = VariableEntity.COLUMN_CATEGORY,
        parentColumn = CategoryEntity.COLUMN_ID,
    )
    val variableResults: List<VariableResult> = emptyList(),
)

package com.speedrun.domain.datasource.categories.mapper

import com.speedrun.domain.data.database.result.VariableResult
import com.speedrun.domain.repo.categories.model.VariableModel

fun VariableResult.toVariableModel(): VariableModel {
    return with(variableEntity){
        VariableModel(
            id = id,
            name = name,
            category = categoryId,
            scope = scope,
            mandatory = mandatory,
            userDefined = userDefined,
            obsoletes = obsoletes,
            isSubcategory = isSubcategory,
            values = valueEntities.map { it.toValueModel() }
        )
    }
}

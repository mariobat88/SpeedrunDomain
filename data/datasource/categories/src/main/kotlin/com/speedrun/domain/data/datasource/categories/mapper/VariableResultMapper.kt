package com.speedrun.domain.data.datasource.categories.mapper

import com.speedrun.domain.data.database.result.VariableResult
import com.speedrun.domain.data.repo.categories.model.VariableModel

fun VariableResult.toVariableModel(): VariableModel{
    return with(variableEntity){
        VariableModel(
            id = id,
            name = name,
            category = category,
            scope = scope,
            mandatory = mandatory,
            userDefined = userDefined,
            obsoletes = obsoletes,
            isSubcategory = isSubcategory,
            values = valueEntities.map { it.toVariableValueModel() }
        )
    }
}

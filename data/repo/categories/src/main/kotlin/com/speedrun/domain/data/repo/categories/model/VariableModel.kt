package com.speedrun.domain.data.repo.categories.model

data class VariableModel(
    val id: String,
    val name: String,
    val category: String,
    val scope: String,
    val mandatory: Boolean,
    val userDefined: Boolean,
    val obsoletes: Boolean,
    val isSubcategory: Boolean,
)

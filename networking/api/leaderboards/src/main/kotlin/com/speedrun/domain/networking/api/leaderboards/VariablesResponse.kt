package com.speedrun.domain.networking.api.leaderboards

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VariablesResponse(
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "category")
    val category: String,
    @Json(name = "scope")
    val scope: Scope,
    @Json(name = "mandatory")
    val mandatory: Boolean,
    @Json(name = "user-defined")
    val userDefined: Boolean,
    @Json(name = "obsoletes")
    val obsoletes: Boolean,
    @Json(name = "values")
    val values: Values,
    @Json(name = "is-subcategory")
    val isSubcategory: Boolean,
) {
    @JsonClass(generateAdapter = true)
    data class Scope(
        @Json(name = "type")
        val type: String
    )

    @JsonClass(generateAdapter = true)
    data class Values(
        @Json(name = "values")
        val values: Map<String, Label>,
        @Json(name = "default")
        val default: Any?
    ) {
        @JsonClass(generateAdapter = true)
        data class Label(
            @Json(name = "label")
            val label: String,
        )
    }
}

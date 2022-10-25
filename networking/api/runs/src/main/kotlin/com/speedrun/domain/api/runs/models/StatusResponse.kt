package com.speedrun.domain.api.runs.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StatusResponse(
    @Json(name = "status") val status: String,
    @Json(name = "examiner") val examiner: String?,
    @Json(name = "verify-date") val verifyDate: String?
)

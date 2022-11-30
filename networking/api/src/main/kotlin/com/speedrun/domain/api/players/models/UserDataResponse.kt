package com.speedrun.domain.api.players.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDataResponse(
    @Json(name = "data")
    val userResponse: PolymorphicPlayerResponse.UserResponse,
)

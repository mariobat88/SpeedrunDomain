package com.speedrun.domain.api.games.models

import com.speedrun.data.common.enums.RunTimeEnum
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Ruleset(
    @Json(name = "show-milliseconds") val showMilliseconds: Boolean,
    @Json(name = "require-verification") val requireVerification: Boolean,
    @Json(name = "require-video") val requireVideo: Boolean,
    @Json(name = "run-times") val runTimes: List<RunTimeEnum>,
    @Json(name = "default-time") val defaultTime: RunTimeEnum,
    @Json(name = "emulators-allowed") val emulatorsAllowed: Boolean
)
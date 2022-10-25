package com.speedrun.data.common.enums

enum class RunTypeEnum(val jsonValue: String) {
    PER_LEVEL("per-level"),
    PER_GAME("per-game"),
    UNKNOWN("unknown");

    companion object {
        fun fromJsonValue(str: String?) = when (str) {
            PER_LEVEL.jsonValue -> PER_LEVEL
            PER_GAME.jsonValue -> PER_GAME
            else -> UNKNOWN
        }
    }
}

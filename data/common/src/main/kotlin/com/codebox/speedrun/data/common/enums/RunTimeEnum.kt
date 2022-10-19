package com.codebox.speedrun.data.common.enums

enum class RunTimeEnum(val jsonValue: String) {
    REALTIME("realtime"),
    INGAME("ingame"),
    UNKNOWN("unknown");

    companion object {
        fun fromJsonValue(str: String?) = when (str) {
            REALTIME.jsonValue -> REALTIME
            INGAME.jsonValue -> INGAME
            else -> UNKNOWN
        }
    }
}

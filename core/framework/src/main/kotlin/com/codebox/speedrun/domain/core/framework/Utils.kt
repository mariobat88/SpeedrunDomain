package com.codebox.speedrun.domain.core.framework

fun toElapsedTime(playtime:String) : String{
    val stripped = playtime.removePrefix("PT")
    return stripped.lowercase()
}

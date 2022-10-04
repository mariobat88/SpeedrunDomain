package com.codebox.speedrun.domain.core.framework

fun toElapsedTime(playtime:String) : String{
    val stripped = playtime.removePrefix("PT")
    return stripped.lowercase()
}

fun countryFlag(code: String) = code
    .uppercase()
    .split("")
    .filter { it.isNotBlank() }
    .map { it.codePointAt(0) + 0x1F1A5 }
    .joinToString("") { String(Character.toChars(it)) }

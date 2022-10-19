package com.speedrun.domain.core.utils

import java.util.*

fun String.capitalized() = capitalized(Locale.getDefault())

fun String.capitalized(locale: Locale) =
    replaceFirstChar { if (it.isLowerCase()) it.titlecase(locale) else it.toString() }

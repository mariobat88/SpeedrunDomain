package com.speedrun.domain.data.datasource.games.mapper

import com.speedrun.data.common.enums.RunTimeEnum
import com.speedrun.domain.data.database.entities.RunTimeEntity

fun RunTimeEnum.toRunTimeEntity() = RunTimeEntity(
    runTime = this
)

fun RunTimeEntity.toRunTimeEnum() = this.runTime

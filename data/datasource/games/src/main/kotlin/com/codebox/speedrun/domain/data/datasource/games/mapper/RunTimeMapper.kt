package com.codebox.speedrun.domain.data.datasource.games.mapper

import com.codebox.speedrun.data.common.enums.RunTimeEnum
import com.codebox.speedrun.domain.data.database.entities.RunTimeEntity

fun RunTimeEnum.toRunTimeEntity() = RunTimeEntity(
    runTime = this
)

fun RunTimeEntity.toRunTimeEnum() = this.runTime

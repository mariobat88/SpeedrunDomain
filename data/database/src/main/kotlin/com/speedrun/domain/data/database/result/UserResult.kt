package com.speedrun.domain.data.database.result

import androidx.room.Embedded
import androidx.room.Relation
import com.speedrun.domain.data.database.entities.LocationEntity
import com.speedrun.domain.data.database.entities.UserEntity

data class UserResult(
    @Embedded
    val userEntity: UserEntity?,
    @Relation(
        entity = LocationEntity::class,
        entityColumn = LocationEntity.COLUMN_ID,
        parentColumn = UserEntity.COLUMN_LOCATION,
    )
    val locationEntity: LocationEntity?,
)

package com.speedrun.domain.data.database.result

import androidx.room.Embedded
import androidx.room.Relation
import com.speedrun.domain.data.database.entities.GuestEntity
import com.speedrun.domain.data.database.entities.PlayerEntity
import com.speedrun.domain.data.database.entities.UserEntity

data class PlayerResult(
    @Embedded
    val player: PlayerEntity,
    @Relation(
        entity = UserEntity::class,
        entityColumn = UserEntity.COLUMN_ID,
        parentColumn = PlayerEntity.COLUMN_ID,
    )
    val userResult: UserResult?,
    @Relation(
        entity = GuestEntity::class,
        entityColumn = GuestEntity.COLUMN_ID,
        parentColumn = PlayerEntity.COLUMN_ID,
    )
    val guest: GuestEntity?,
)

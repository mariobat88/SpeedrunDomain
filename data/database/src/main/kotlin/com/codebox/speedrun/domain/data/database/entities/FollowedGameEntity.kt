package com.codebox.speedrun.domain.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FollowedGameEntity(
    @PrimaryKey
    val id: String
)
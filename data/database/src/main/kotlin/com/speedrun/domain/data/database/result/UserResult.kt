package com.speedrun.domain.data.database.result

import androidx.room.Embedded
import androidx.room.Relation
import com.speedrun.domain.data.database.entities.CountryEntity
import com.speedrun.domain.data.database.entities.UserEntity

data class UserResult(
    @Embedded
    val user: UserEntity?,
    @Relation(
        entity = CountryEntity::class,
        entityColumn = CountryEntity.COLUMN_CODE,
        parentColumn = UserEntity.COLUMN_COUNTRY_CODE,
    )
    val countryEntity: CountryEntity?,
)
package com.speedrun.domain.data.database.daos

import androidx.room.Dao
import com.speedrun.domain.data.database.entities.CountryEntity

@Dao
abstract class CountryDao : BaseDao<CountryEntity>()

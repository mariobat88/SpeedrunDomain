package com.speedrun.domain.data.database.daos

import androidx.room.Dao
import com.speedrun.domain.data.database.entities.VideoEntity

@Dao
abstract class VideoDao : BaseDao<VideoEntity>()

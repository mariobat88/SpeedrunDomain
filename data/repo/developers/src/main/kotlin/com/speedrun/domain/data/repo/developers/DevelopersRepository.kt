package com.speedrun.domain.data.repo.developers

import com.speedrun.domain.data.repo.developers.model.DeveloperModel

interface DevelopersRepository {

    suspend fun getDeveloper(id: String): DeveloperModel
}

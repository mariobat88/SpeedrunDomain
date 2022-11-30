package com.speedrun.domain.repo.developers

import com.speedrun.domain.repo.developers.model.DeveloperModel

interface DevelopersRepository {

    suspend fun getDeveloper(id: String): DeveloperModel
}

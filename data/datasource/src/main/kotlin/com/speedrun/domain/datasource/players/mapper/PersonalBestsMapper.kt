package com.speedrun.domain.datasource.players.mapper

import com.speedrun.domain.api.players.models.PersonalBestsDataListResponse
import com.speedrun.domain.data.database.entities.PlaceEntity
import com.speedrun.domain.data.database.entities.RunPlayerEntity
import com.speedrun.domain.data.database.result.RunPlaceResult
import com.speedrun.domain.datasource.runs.mapper.toRunModel
import com.speedrun.domain.repo.players.model.RunPositionModel

fun PersonalBestsDataListResponse.PersonalBestData.toPlaceEntity() = PlaceEntity(
    runId = run.id,
    place = place
)


fun PersonalBestsDataListResponse.PersonalBestData.toRunPlayerEntity(
    playerId: String,
) = RunPlayerEntity(
    runId = run.id,
    playerId = playerId,
)


fun RunPlaceResult.toRunPosition() = RunPositionModel(
    place = placeEntity.place,
    runModel = runResult.toRunModel(),
)

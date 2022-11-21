package com.speedrun.domain.feature.run

import com.speedrun.domain.core.framework.async.Async
import com.speedrun.domain.core.framework.async.Loading
import com.speedrun.domain.repo.runs.model.RunModel

sealed class Intent {

}

data class ViewState(
    val runAsync: Async<RunModel> = Loading()
)
package com.codebox.speedrun.domain.feature.game

import com.codebox.speedrun.domain.core.framework.SpeedrunViewModel
import com.codebox.speedrun.domain.wrapper.dispatchers.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    dispatcherProvider: DispatcherProvider,
) : SpeedrunViewModel<ViewState, Intent, Unit>(
    viewState = ViewState()
) {

}
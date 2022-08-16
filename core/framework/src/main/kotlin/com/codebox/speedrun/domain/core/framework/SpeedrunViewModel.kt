package com.codebox.speedrun.domain.core.framework

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class SpeedrunViewModel<VS, I, E>(
    viewState: VS
) : ViewModel() {

    protected val _viewState: MutableStateFlow<VS> = MutableStateFlow(viewState)

    val viewState = _viewState.asStateFlow()

    protected fun reduce(block: (VS) -> VS) {
        _viewState.update { block(it) }
    }
}

package com.codebox.speedrun.domain.core.framework

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class SpeedrunViewModel<VS, I, E>(
    viewState: VS
) : ViewModel() {

    protected val _viewState: MutableStateFlow<VS> = MutableStateFlow(viewState)

    val intentChannel = MutableSharedFlow<I>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )

    protected val _sideEffects = Channel<E>(capacity = Int.MAX_VALUE)

    val viewState = _viewState.asStateFlow()
    val sideEffects = _sideEffects.receiveAsFlow()

    init {
        viewModelScope.launch {
            bind(intentChannel).collect()
        }
    }

    protected open suspend fun bind(intents: Flow<I>): Flow<Any> {
        return emptyFlow()
    }

    protected fun reduce(block: (VS) -> VS) {
        _viewState.update { block(it) }
    }

    open suspend fun onBackClicked() { }
}

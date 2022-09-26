package com.codebox.speedrun.domain.core.framework

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

@Composable
private fun <ViewState : Any, Intent : Any, SideEffect : Any> RegisterBackHandler(viewModel: SpeedrunViewModel<ViewState, Intent, SideEffect>) {
    val backHandlerScope = rememberCoroutineScope()
    BackHandler(true) {
        backHandlerScope.launch { viewModel.onBackClicked() }
    }
}

@Composable
fun <ViewState : Any, Intent : Any, SideEffect : Any> Compose(
    viewModel: SpeedrunViewModel<ViewState, Intent, SideEffect>,
    block: @Composable (viewState: ViewState, intentChannel: MutableSharedFlow<Intent>, sideEffects: Flow<SideEffect>) -> Unit,
) {
    block(viewModel.viewState.collectAsStateWithLifecycle().value, viewModel.intentChannel, viewModel.sideEffects)
}

package com.speedrun.domain.core.framework

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.speedrun.domain.core.framework.navigation.NavigationState
import com.speedrun.domain.core.framework.navigation.StateNavigator
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
    block(
        viewModel.viewState.collectAsStateWithLifecycle().value,
        viewModel.intentChannel,
        viewModel.sideEffects
    )
}

@Composable
fun <ViewState : Any, Intent : Any, SideEffect : Any> Screen(
    viewModel: SpeedrunViewModel<ViewState, Intent, SideEffect>,
    block: @Composable (viewState: ViewState, intentChannel: MutableSharedFlow<Intent>, sideEffects: Flow<SideEffect>) -> Unit,
) {
    //RegisterBackHandler(viewModel)
    block(
        viewModel.viewState.collectAsStateWithLifecycle().value,
        viewModel.intentChannel,
        viewModel.sideEffects
    )
}

@Composable
fun Navigation(
    navController: NavController,
    stateNavigator: StateNavigator
) {
    when (val navigationState = stateNavigator.navigationState.collectAsState().value) {
        is NavigationState.NavigateToRoute -> {
            navController.navigate(navigationState.route, navigationState.navOptions)
            stateNavigator.onNavigated(navigationState)
        }
        is NavigationState.PopToRoute -> {
            navController.popBackStack(navigationState.staticRoute, false)
            stateNavigator.onNavigated(navigationState)
        }
        is NavigationState.NavigateUp -> {
            navController.navigateUp()
        }
        is NavigationState.Idle -> {
        }
    }
}

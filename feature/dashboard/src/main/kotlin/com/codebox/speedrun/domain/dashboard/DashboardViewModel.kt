package com.codebox.speedrun.domain.dashboard

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavOptions
import com.codebox.speedrun.domain.core.framework.SpeedrunViewModel
import com.codebox.speedrun.domain.core.framework.navigation.StateNavigator
import com.codebox.speedrun.domain.core.framework.navigation.StateNavigatorImpl
import com.codebox.speedrun.domain.dashboard.navigation.DashboardNavigator
import com.codebox.speedrun.domain.dashboard.navigation.DashboardSubNavigator
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.onEach

class DashboardViewModel @AssistedInject constructor(
    @Assisted("stateNavigator") private val stateNavigator: StateNavigator,
    @Assisted("dashboardNavigator") val dashboardNavigator: DashboardNavigator,
) : SpeedrunViewModel<Unit, Intent, Unit>(
    viewState = Unit
), DashboardSubNavigator, StateNavigator by stateNavigator {

    @EntryPoint
    @InstallIn(ActivityComponent::class)
    interface ViewModelFactoryProvider {
        fun dashboardViewModelFactory(): Factory
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("stateNavigator") stateNavigator: StateNavigator,
            @Assisted("dashboardNavigator") dashboardNavigator: DashboardNavigator,
        ): DashboardViewModel
    }

    companion object {
        @Composable
        fun create(
            dashboardNavigator: DashboardNavigator
        ): DashboardViewModel {
            val activity = LocalContext.current as Activity

            return viewModel(
                factory = object : ViewModelProvider.Factory {
                    @Suppress("UNCHECKED_CAST")
                    override fun <T : ViewModel> create(
                        modelClass: Class<T>,
                        extras: CreationExtras
                    ): T {
                        val entryPoint = EntryPointAccessors.fromActivity(
                            activity,
                            ViewModelFactoryProvider::class.java
                        )
                        return entryPoint.dashboardViewModelFactory()
                            .create(
                                stateNavigator = StateNavigatorImpl(),
                                dashboardNavigator = dashboardNavigator,
                            ) as T
                    }
                })
        }
    }

    override suspend fun bind(intents: Flow<Intent>): Flow<Any> {
        return intents.filterIsInstance<Intent.NavigateToTab>()
            .onEach { intent -> navigateToTab(intent.route, intent.navOptions) }
    }

    override fun navigateToTab(route: String, navOptions: NavOptions) {
        stateNavigator.navigateToRoute(route, navOptions)
    }
}

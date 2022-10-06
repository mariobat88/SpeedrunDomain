package com.codebox.speedrun.domain.navigation

import com.codebox.speedrun.domain.dashboard.DASHBOARD_SCREEN_ROUTE
import com.codebox.speedrun.domain.dashboard.destinations.DashboardScreenDestination
import com.codebox.speedrun.domain.feature.game.GAME_SCREEN_ROUTE
import com.codebox.speedrun.domain.feature.game.destinations.GameScreenDestination
import com.ramcosta.composedestinations.dynamic.routedIn
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec

object AppNavGraphs {
    val dashboard = object : NavGraphSpec {
        override val route = DASHBOARD_SCREEN_ROUTE

        override val startRoute = DashboardScreenDestination routedIn this

        override val destinationsByRoute = listOf<DestinationSpec<*>>(
            DashboardScreenDestination,
        ).routedIn(this)
            .associateBy { it.route }
    }

    val game = object : NavGraphSpec {
        override val route = GAME_SCREEN_ROUTE

        override val startRoute = GameScreenDestination routedIn this

        override val destinationsByRoute = listOf<DestinationSpec<*>>(
            GameScreenDestination,
        ).routedIn(this)
            .associateBy { it.route }
    }

    val root = object : NavGraphSpec {
        override val route = "root"
        override val startRoute = dashboard
        override val destinationsByRoute = listOf<DestinationSpec<*>>(
            DashboardScreenDestination,
            GameScreenDestination
        ).routedIn(this)
            .associateBy { it.route }
        override val nestedNavGraphs = listOf(
            dashboard,
            game,
        )
    }
}

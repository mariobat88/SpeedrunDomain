package com.codebox.speedrun.domain.dashboard

import com.codebox.speedrun.domain.dashboard.destinations.HomeScreenDestination
import com.codebox.speedrun.domain.dashboard.destinations.LatestRunsScreenDestination
import com.codebox.speedrun.domain.dashboard.destinations.SearchScreenDestination
import com.codebox.speedrun.domain.dashboard.home.HOME_SCREEN_ROUTE
import com.codebox.speedrun.domain.dashboard.search.SEARCH_SCREEN_ROUTE
import com.ramcosta.composedestinations.dynamic.routedIn
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec

object DashboardNavGraphs {
    val home = object : NavGraphSpec {
        override val route = HOME_SCREEN_ROUTE

        override val startRoute = HomeScreenDestination routedIn this

        override val destinationsByRoute = listOf<DestinationSpec<*>>(
            HomeScreenDestination,
        ).routedIn(this)
            .associateBy { it.route }
    }

    val search = object : NavGraphSpec {
        override val route = SEARCH_SCREEN_ROUTE

        override val startRoute = SearchScreenDestination routedIn this

        override val destinationsByRoute = listOf<DestinationSpec<*>>(
            SearchScreenDestination,
        ).routedIn(this)
            .associateBy { it.route }
    }

    val latestRuns = object : NavGraphSpec {
        override val route = LATEST_SCREEN_ROUTE

        override val startRoute = LatestRunsScreenDestination routedIn this

        override val destinationsByRoute = listOf<DestinationSpec<*>>(
            LatestRunsScreenDestination,
        ).routedIn(this)
            .associateBy { it.route }
    }

    val root = object : NavGraphSpec {
        override val route = "root"
        override val startRoute = home
        override val destinationsByRoute = emptyMap<String, DestinationSpec<*>>()
        override val nestedNavGraphs = listOf(
            home,
            search,
            latestRuns,
        )
    }
}

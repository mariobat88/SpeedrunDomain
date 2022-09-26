package com.codebox.speedrun.domain.dashboard

import com.codebox.speedrun.domain.dashboard.destinations.HomeScreenDestination
import com.codebox.speedrun.domain.dashboard.destinations.LatestRunsScreenDestination
import com.codebox.speedrun.domain.dashboard.destinations.SearchScreenDestination

enum class DashboardTabs(val titleRes: Int, val route: String) {
    Home(R.string.home, HomeScreenDestination.route),
    Search(R.string.search, SearchScreenDestination.route),
    LatestRuns(R.string.latest_runs, LatestRunsScreenDestination.route),
    Profile(R.string.profile, LatestRunsScreenDestination.route)
}

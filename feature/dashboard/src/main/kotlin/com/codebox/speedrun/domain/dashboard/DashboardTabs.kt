package com.codebox.speedrun.domain.dashboard

import com.codebox.speedrun.domain.dasboard.R
import com.codebox.speedrun.domain.dashboard.destinations.HomeScreenDestination
import com.codebox.speedrun.domain.dashboard.destinations.LatestRunsScreenDestination

enum class DashboardTabs(val titleRes: Int, val route: String) {
    Home(R.string.home, HomeScreenDestination.route),
    LatestRuns(R.string.latest_runs, LatestRunsScreenDestination.route),
    Profile(R.string.profile, LatestRunsScreenDestination.route)
}

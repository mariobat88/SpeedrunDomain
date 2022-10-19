package com.speedrun.domain.dashboard

import com.speedrun.domain.dashboard.feature.home.navigation.HomeNavigation
import com.speedrun.domain.dashboard.feature.search.navigation.SearchNavigation
import com.speedrun.domain.feature.dashboard.R

enum class DashboardTabs(val titleRes: Int, val route: String) {
    Home(R.string.home, HomeNavigation.route),
    Search(R.string.search, SearchNavigation.route),
    LatestRuns(R.string.latest_runs, "latest_runs"),
    Profile(R.string.profile, "latest_runs")
}

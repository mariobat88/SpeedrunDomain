package com.codebox.speedrun.domain.dashboard

import com.codebox.speedrun.domain.dashboard.feature.home.navigation.HomeNavigation
import com.codebox.speedrun.domain.dashboard.feature.search.navigation.SearchNavigation

enum class DashboardTabs(val titleRes: Int, val route: String) {
    Home(R.string.home, HomeNavigation.route),
    Search(R.string.search, SearchNavigation.route),
    LatestRuns(R.string.latest_runs, "latest_runs"),
    Profile(R.string.profile, "latest_runs")
}

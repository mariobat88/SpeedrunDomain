package com.codebox.speedrun.domain.dashboard

enum class DashboardTabs(val titleRes: Int, val route: String) {
    Home(R.string.home, "home"),
    Search(R.string.search, "search"),
    LatestRuns(R.string.latest_runs, "latest_runs"),
    Profile(R.string.profile, "latest_runs")
}

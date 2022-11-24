plugins {
    id("speedrun.domain.android.datasource")
}

dependencies{
    api(projects.data.datasource.categories)
    api(projects.data.datasource.games)
    api(projects.data.datasource.players)
    api(projects.data.datasource.runs)
    api(projects.data.repo.leaderboards)
    api(projects.networking.api.leaderboards)
}

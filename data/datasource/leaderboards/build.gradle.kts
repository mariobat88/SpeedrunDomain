plugins {
    id("speedrun.domain.android.datasource")
}

dependencies{
    api(projects.data.repo.leaderboards)
    api(projects.networking.api.leaderboards)
}

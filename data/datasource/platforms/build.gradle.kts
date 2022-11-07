plugins {
    id("speedrun.domain.android.datasource")
}

dependencies{
    api(projects.data.repo.platforms)
    api(projects.networking.api.platforms)
}

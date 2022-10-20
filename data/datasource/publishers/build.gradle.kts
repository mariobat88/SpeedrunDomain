plugins {
    id("speedrun.domain.android.datasource")
}

dependencies{
    api(projects.data.repo.publishers)
    api(projects.networking.api.publishers)
}

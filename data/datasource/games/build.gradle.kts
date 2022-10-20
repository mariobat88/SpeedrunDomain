plugins {
    id("speedrun.domain.android.datasource")
}

dependencies{
    api(projects.data.datasource.developers)
    api(projects.data.repo.games)
    api(projects.networking.api.games)
}

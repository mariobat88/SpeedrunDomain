plugins {
    id("speedrun.domain.android.datasource")
}

dependencies{
    api(projects.data.repo.developers)
    api(projects.networking.api.developers)
}

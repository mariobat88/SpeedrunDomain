plugins {
    id("speedrun.domain.android.api")
}

dependencies{
    api(projects.networking.api.pagination)
    api(projects.networking.api.platforms)
}

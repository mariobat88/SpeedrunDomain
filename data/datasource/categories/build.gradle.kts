plugins {
    id("speedrun.domain.android.datasource")
}

dependencies{
    api(projects.data.repo.categories)
    api(projects.networking.api.categories)
}

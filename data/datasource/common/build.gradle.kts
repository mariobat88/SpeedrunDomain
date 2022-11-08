plugins {
    id("speedrun.domain.android.library")
}

dependencies{
    api(projects.networking.api.common)
    api(projects.data.database)
    api(projects.data.repo.common)
}

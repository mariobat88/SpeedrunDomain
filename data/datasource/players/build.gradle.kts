plugins {
    id("speedrun.domain.android.datasource")
}

dependencies{
    api(projects.data.repo.players)
    api(projects.networking.api.players)
}
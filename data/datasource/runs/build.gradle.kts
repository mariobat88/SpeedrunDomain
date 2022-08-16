plugins {
    id("speedrun.domain.android.datasource")
}

dependencies{
    implementation(projects.data.datasource.categories)
    implementation(projects.data.datasource.games)
    api(projects.data.repo.runs)
    api(projects.networking.api.runs)
}

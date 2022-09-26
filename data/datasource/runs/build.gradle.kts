plugins {
    id("speedrun.domain.android.datasource")
}

dependencies{
    implementation(projects.data.database)
    implementation(projects.data.datasource.categories)
    implementation(projects.data.datasource.games)
    implementation(projects.data.datasource.players)
    implementation(projects.data.datasource.players)
    api(projects.data.repo.runs)
    api(projects.networking.api.runs)
}

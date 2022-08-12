plugins {
    id("speedrun.domain.android.datasource")
}

dependencies{
    implementation(projects.data.repo.runs)
    implementation(projects.networking.api.runs)
}

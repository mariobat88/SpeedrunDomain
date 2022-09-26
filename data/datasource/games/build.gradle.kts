plugins {
    id("speedrun.domain.android.datasource")
}

dependencies{
    implementation(libs.room.runtime)
    implementation(projects.data.database)
    api(projects.data.repo.games)
    api(projects.networking.api.games)
}

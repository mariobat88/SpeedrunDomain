plugins {
    id("speedrun.domain.android.api")
}

dependencies{
    implementation(projects.networking.api.players)
    implementation(projects.networking.api.runs)
}

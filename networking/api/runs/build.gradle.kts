plugins {
    id("speedrun.domain.android.api")
}

dependencies{
    implementation(projects.networking.api.categories)
    implementation(projects.networking.api.games)
    implementation(projects.networking.api.players)
}

plugins {
    id("speedrun.domain.android.api")
}

dependencies{
    api(projects.networking.api.pagination)
    implementation(projects.networking.core)
}

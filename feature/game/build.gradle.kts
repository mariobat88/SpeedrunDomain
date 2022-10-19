plugins {
    id("speedrun.domain.android.feature")
}

dependencies{
    implementation(libs.coil)
    implementation(projects.data.repo.games)
    implementation(projects.core.utils)
}

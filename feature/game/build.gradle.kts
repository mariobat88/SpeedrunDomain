plugins {
    id("speedrun.domain.android.feature")
}

dependencies{
    implementation(libs.coil)
    implementation(projects.data.repo.developers)
    implementation(projects.data.repo.games)
    implementation(projects.data.repo.publishers)
    implementation(projects.kit.run)
    implementation(projects.core.utils)
}

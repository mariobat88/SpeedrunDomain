plugins {
    id("speedrun.domain.android.feature")
}

dependencies{
    implementation(libs.coil)
    implementation(projects.data.repo)
    implementation(projects.kit.run)
    implementation(projects.core.utils)
}

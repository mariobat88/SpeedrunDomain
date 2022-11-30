plugins {
    id("speedrun.domain.android.feature")
}

dependencies {
    implementation(libs.coil)
    implementation(libs.accompanist.placeholderMaterial)
    implementation(projects.core.paging)
    implementation(projects.data.repo)
    implementation(projects.kit.player)
}

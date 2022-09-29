plugins {
    id("speedrun.domain.android.feature")
}

dependencies {
    implementation(libs.coil)
    implementation(libs.accompanist.placeholderMaterial)
    implementation(projects.core.paging)
    implementation(projects.data.repo.games)
    implementation(projects.data.repo.runs)
    implementation(projects.kit.player)
}

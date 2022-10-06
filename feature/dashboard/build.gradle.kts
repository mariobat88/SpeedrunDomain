plugins {
    id("speedrun.domain.android.feature")
}

android{
    ksp {
        arg("compose-destinations.moduleName", "game")
        arg("compose-destinations.mode", "destinations")
    }
}

dependencies {
    implementation(libs.coil)
    implementation(libs.accompanist.placeholderMaterial)
    implementation(projects.core.paging)
    implementation(projects.data.repo.games)
    implementation(projects.data.repo.runs)
    implementation(projects.kit.player)
}

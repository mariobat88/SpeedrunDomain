plugins {
    id("speedrun.domain.android.library.compose")
}

dependencies{
    implementation(projects.core.ui)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material3)
    implementation(libs.coil)
    api(projects.data.repo.players)
}

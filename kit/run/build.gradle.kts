plugins {
    id("speedrun.domain.android.library.compose")
}

dependencies {
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material)
    implementation(libs.coil)
    implementation(projects.core.designsystem)
    implementation(projects.core.ui)
    api(projects.data.repo.runs)
}

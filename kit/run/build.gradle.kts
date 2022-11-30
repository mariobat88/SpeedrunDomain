plugins {
    id("speedrun.domain.android.library.compose")
}

dependencies {
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material3)
    implementation(libs.coil)
    implementation(projects.core.designsystem)
    implementation(projects.core.ui)
    implementation(projects.data.repo)
}

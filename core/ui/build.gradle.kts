plugins {
    id("speedrun.domain.android.library.compose")
}

dependencies{
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material3)
    implementation(projects.core.designsystem)
}

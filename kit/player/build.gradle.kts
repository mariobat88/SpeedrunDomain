plugins {
    id("speedrun.domain.android.library.compose")
}

dependencies{
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material)
    api(projects.data.repo.players)
}

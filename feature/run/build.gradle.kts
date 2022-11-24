plugins {
    id("speedrun.domain.android.feature")
}

dependencies {
    implementation(files("libs/YouTubeAndroidPlayerApi.jar"))
    implementation(projects.data.repo.leaderboards)
    implementation(projects.kit.player)
    implementation(libs.androidx.appcompat)
    implementation(libs.coil)
}
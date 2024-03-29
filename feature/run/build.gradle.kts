plugins {
    id("speedrun.domain.android.feature")
}

dependencies {
    implementation(files("libs/YouTubeAndroidPlayerApi.jar"))
    implementation(libs.androidx.appcompat)
    implementation(libs.coil)
    implementation(projects.core.annotations)
    implementation(projects.data.repo)
    implementation(projects.kit.leaderboard)
    implementation(projects.kit.player)
    implementation(projects.kit.run)
}
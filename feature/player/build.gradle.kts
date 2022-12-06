plugins {
    id("speedrun.domain.android.feature")
}

dependencies{
    implementation(libs.coil)
    implementation(projects.data.repo)
    implementation(projects.kit.player)
    implementation(projects.kit.leaderboard)
    implementation(projects.kit.run)
}
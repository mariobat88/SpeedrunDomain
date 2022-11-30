plugins {
    id("speedrun.domain.android.feature")
}

dependencies{
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pagerIndicators)
    implementation(libs.coil)
    implementation(projects.data.repo)
    implementation(projects.kit.player)
    implementation(projects.kit.run)
}

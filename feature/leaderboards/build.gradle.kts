plugins {
    id("speedrun.domain.android.feature")
}

dependencies{
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pagerIndicators)
    implementation(projects.data.repo.categories)
    implementation(projects.data.repo.leaderboards)
    implementation(projects.kit.player)
}

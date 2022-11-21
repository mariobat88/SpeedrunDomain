plugins {
    id("speedrun.domain.android.feature")
}

dependencies {
    implementation(files("libs/YouTubeAndroidPlayerApi.jar"))
    implementation(projects.data.repo.runs)
    implementation(libs.androidx.appcompat)
}
plugins {
    id("speedrun.domain.android.library")
    id("speedrun.domain.android.hilt")
}

dependencies {
    implementation(projects.core.annotations)
    implementation(projects.core.initializer)
    implementation(libs.timber)
}

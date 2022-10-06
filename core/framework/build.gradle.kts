plugins {
    id("speedrun.domain.android.library.compose")
}

dependencies{
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.compose.runtime)
    implementation(libs.compose.destinations.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.javax.inject)
}

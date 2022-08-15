plugins {
    id("speedrun.domain.android.library")
    id("org.jetbrains.kotlin.kapt")
}

dependencies{
    implementation(libs.moshi.core)
    kapt(libs.moshi.kotlinCodegen)
}

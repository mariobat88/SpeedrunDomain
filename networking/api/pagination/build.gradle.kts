plugins {
    id("speedrun.domain.java.library")
    id("org.jetbrains.kotlin.kapt")
}

dependencies{
    implementation(libs.moshi.core)
    implementation(projects.networking.api.common)
    kapt(libs.moshi.kotlinCodegen)
}

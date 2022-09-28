plugins {
    id("speedrun.domain.java.library")
    id("org.jetbrains.kotlin.kapt")
}

dependencies{
    implementation(libs.moshi.core)
    kapt(libs.moshi.kotlinCodegen)
}

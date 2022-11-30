plugins {
    id("speedrun.domain.android.library")
    id("speedrun.domain.android.hilt")
}

dependencies {
    implementation(libs.retrofit.core)
    implementation(libs.moshi.adapters)
    implementation(libs.moshi.core)
    implementation(libs.okhttp.core)
    implementation(libs.okhttp.loggingInterceptor)
    implementation(libs.retrofit.converterMoshi)
    implementation(projects.core.annotations)
    implementation(projects.data.common)
    implementation(projects.networking.api)
    kapt(libs.moshi.kotlinCodegen)
}

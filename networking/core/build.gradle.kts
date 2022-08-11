plugins {
    id("speedrun.domain.android.library")
    id("speedrun.domain.android.hilt")
}

dependencies {
    implementation(libs.retrofit.core)
    implementation(libs.moshi.core)
    implementation(libs.okhttp.core)
    implementation(libs.okhttp.loggingInterceptor)
    implementation(libs.retrofit.converterMoshi)
    implementation(projects.common.annotations)
    kapt(libs.moshi.kotlinCodegen)
}

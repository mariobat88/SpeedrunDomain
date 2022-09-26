plugins {
    id("speedrun.domain.android.library")
    id("speedrun.domain.android.hilt")
    id("com.google.devtools.ksp")
}

dependencies{
    implementation(libs.room.ktx)
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
}
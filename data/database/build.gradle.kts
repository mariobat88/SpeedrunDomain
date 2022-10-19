plugins {
    id("speedrun.domain.android.library")
    id("speedrun.domain.android.hilt")
    id("com.google.devtools.ksp")
}

dependencies{
    implementation(libs.room.ktx)
    implementation(libs.room.runtime)
    implementation(projects.data.common)
    ksp(libs.room.compiler)
}
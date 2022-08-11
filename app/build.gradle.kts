import com.speedrun.domain.app

plugins {
    id("speedrun.domain.android.application.compose")
    kotlin("kapt")
}

android {
    defaultConfig {
        applicationId = app.applicationId

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            buildConfigField("String", "API_URL", "\"https://www.speedrun.com/api/v1\"")
        }
        debug {
            isDebuggable = true

            buildConfigField("String", "API_URL", "\"https://www.speedrun.com/api/v1\"")
        }
    }
}

dependencies {
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.compose.destinations.core)
    implementation(libs.hilt.android)
    implementation(libs.material3)

    implementation(projects.common.annotations)
    implementation(projects.common.designsystem)
    implementation(projects.feature.dashboard)
    implementation(projects.networking.core)

    kapt(libs.hilt.compiler)
    ksp(libs.compose.destinations.ksp)
}

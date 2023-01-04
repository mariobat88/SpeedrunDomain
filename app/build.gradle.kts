import com.speedrun.domain.app
import java.util.*

plugins {
    id("speedrun.domain.android.application.compose")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

fun configuration(name: String): String? {
    if (project.rootProject.file("config.properties").exists()) {
        val properties = Properties()
        properties.load(project.rootProject.file("config.properties").inputStream())
        return properties.getProperty(name)
    }
    return null
}

android {
    defaultConfig {
        applicationId = app.applicationId
        versionName = app.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    val youtubeApiKey = configuration("YOUTUBE_API_KEY") ?: ""

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            buildConfigField("String", "API_URL", "\"https://www.speedrun.com/api/v1/\"")
            buildConfigField("String", "YOUTUBE_API_KEY", youtubeApiKey)
        }
        debug {
            isDebuggable = true

            buildConfigField("String", "API_URL", "\"https://www.speedrun.com/api/v1/\"")
            buildConfigField("String", "YOUTUBE_API_KEY", youtubeApiKey)
        }
    }
}

dependencies {
    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)

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
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.hilt.android)
    implementation(libs.material3)

    implementation(projects.core.annotations)
    implementation(projects.core.designsystem)
    implementation(projects.core.framework)
    implementation(projects.core.initializer)
    implementation(projects.core.navigation)
    implementation(projects.core.wrapper.logging)
    implementation(projects.data.database)
    implementation(projects.data.datasource)
    implementation(projects.feature.dashboard)
    implementation(projects.feature.game)
    implementation(projects.feature.leaderboards)
    implementation(projects.feature.player)
    implementation(projects.feature.run)
    implementation(projects.networking.core)

    kapt(libs.hilt.compiler)
}

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("javaLibrary") {
            id = "speedrun.domain.java.library"
            implementationClass = "JavaLibraryConventionPlugin"
        }
        register("androidLibrary") {
            id = "speedrun.domain.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "speedrun.domain.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidApplication") {
            id = "speedrun.domain.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "speedrun.domain.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidHilt") {
            id = "speedrun.domain.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("speedrunDomainFeature") {
            id = "speedrun.domain.android.feature"
            implementationClass = "SpeedrunDomainFeatureConventionPlugin"
        }
        register("speedrunDomainApi") {
            id = "speedrun.domain.android.api"
            implementationClass = "SpeedrunDomainApiConventionPlugin"
        }
//        register("speedrunDomainRepo") {
//            id = "speedrun.domain.android.repo"
//            implementationClass = "DomainRepoConventionPlugin"
//        }
//        register("speedrunDomainDatasource") {
//            id = "speedrun.domain.android.datasource"
//            implementationClass = "DomainDatasourceConventionPlugin"
//        }
//        register("test") {
//            id = "speedrun.domain.android.test"
//            implementationClass = "DomainTestConventionPlugin"
//        }
//        register("androidTest") {
//            id = "speedrun.domain.android.androidTest"
//            implementationClass = "DomainAndroidTestConventionPlugin"
//        }
    }
}

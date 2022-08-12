@file:Suppress("UnstableApiUsage")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "SpeedrunDomain"
include(":app")
include(":common:annotations")
include(":common:designsystem")
include(":common:wrapper:dispatchers")
include(":data:datasource:runs")
include(":data:repo:runs")
include(":feature:dashboard")
include(":networking:api:runs")
include(":networking:core")

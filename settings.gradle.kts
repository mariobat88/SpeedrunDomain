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
include(":core:annotations")
include(":core:designsystem")
include(":core:framework")
include(":core:paging")
include(":core:ui")
include(":core:wrapper:dispatchers")
include(":data:database")
include(":data:datasource:categories")
include(":data:datasource:common")
include(":data:datasource:games")
include(":data:datasource:players")
include(":data:datasource:runs")
include(":data:pagination")
include(":data:repo:categories")
include(":data:repo:common")
include(":data:repo:games")
include(":data:repo:players")
include(":data:repo:runs")
include(":feature:dashboard")
include(":kit:player")
include(":networking:api:categories")
include(":networking:api:common")
include(":networking:api:games")
include(":networking:api:pagination")
include(":networking:api:players")
include(":networking:api:runs")
include(":networking:core")

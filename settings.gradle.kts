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
include(":core:initializer")
include(":core:navigation")
include(":core:paging")
include(":core:ui")
include(":core:utils")
include(":core:wrapper:dispatchers")
include(":core:wrapper:logging")
include(":data:common")
include(":data:database")
include(":data:datasource")
include(":data:repo")
include(":feature:dashboard")
include(":feature:game")
include(":feature:leaderboards")
include(":feature:player")
include(":feature:run")
include(":kit:player")
include(":kit:run")
include(":networking:api")
include(":networking:core")

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
include(":data:datasource:categories")
include(":data:datasource:common")
include(":data:datasource:developers")
include(":data:datasource:games")
include(":data:datasource:leaderboards")
include(":data:datasource:platforms")
include(":data:datasource:players")
include(":data:datasource:publishers")
include(":data:datasource:runs")
include(":data:pagination")
include(":data:repo:categories")
include(":data:repo:common")
include(":data:repo:developers")
include(":data:repo:games")
include(":data:repo:leaderboards")
include(":data:repo:platforms")
include(":data:repo:players")
include(":data:repo:publishers")
include(":data:repo:runs")
include(":feature:dashboard")
include(":feature:game")
include(":feature:leaderboards")
include(":feature:run")
include(":kit:player")
include(":kit:run")
include(":networking:api:categories")
include(":networking:api:common")
include(":networking:api:developers")
include(":networking:api:games")
include(":networking:api:leaderboards")
include(":networking:api:pagination")
include(":networking:api:platforms")
include(":networking:api:players")
include(":networking:api:publishers")
include(":networking:api:runs")
include(":networking:core")

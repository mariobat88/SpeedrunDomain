import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

@Suppress("unused", "UnstableApiUsage")
class SpeedrunDomainApiConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("speedrun.domain.android.library")
                apply("speedrun.domain.android.hilt")
            }

            val libs = extensions.getByType(VersionCatalogsExtension::class).named("libs")

            dependencies {
                add("implementation", libs.findLibrary("moshi.core").get())
                add("implementation", libs.findLibrary("retrofit.converterMoshi").get())
                add("implementation", libs.findLibrary("retrofit.core").get())
                add("implementation", project(":data:common"))
                add("kapt", libs.findLibrary("moshi.kotlinCodegen").get())
            }
        }
    }
}

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

@Suppress("unused", "UnstableApiUsage")
class SpeedrunDomainFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("speedrun.domain.android.library.compose")
                apply("speedrun.domain.android.hilt")
                apply("com.google.devtools.ksp")
            }

            extensions.configure<LibraryExtension> {
                libraryVariants.all {
                    sourceSets {
                        getByName(name) {
                            kotlin.srcDir("build/generated/ksp/$name/kotlin")
                        }
                    }
                }
            }

            val libs = extensions.getByType(VersionCatalogsExtension::class).named("libs")

            dependencies {
                add("implementation", libs.findLibrary("accompanist.systemuicontroller").get())
                add("implementation", libs.findLibrary("compose.destinations.core").get())
                add("implementation", libs.findLibrary("androidx.compose.foundation").get())
                add("implementation", libs.findLibrary("androidx.compose.foundation.layout").get())
                add("implementation", libs.findLibrary("androidx.compose.material3").get())
                add("implementation", libs.findLibrary("androidx.compose.ui.tooling").get())
                add("implementation", libs.findLibrary("androidx.compose.ui.tooling.preview").get())
                add("implementation", libs.findLibrary("androidx.hilt.navigation.compose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.compose.runtime").get())
                add("implementation", project(":core:designsystem"))
                add("implementation", project(":core:framework"))
                add("implementation", project(":core:ui"))
                add("implementation", project(":core:wrapper:dispatchers"))
                add("ksp", libs.findLibrary("compose.destinations.ksp").get())
            }
        }
    }
}

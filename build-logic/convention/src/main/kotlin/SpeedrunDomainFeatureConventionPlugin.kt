import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.speedrun.domain.configureAndroidCompose
import com.speedrun.domain.configureComposeDestinations
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
                configureComposeDestinations()
            }

            val libs = extensions.getByType(VersionCatalogsExtension::class).named("libs")

            dependencies {
                add("implementation", libs.findLibrary("accompanist.systemuicontroller").get())
                add("implementation", libs.findLibrary("compose.destinations.core").get())
                add("implementation", libs.findLibrary("androidx.compose.foundation").get())
                add("implementation", libs.findLibrary("androidx.compose.foundation.layout").get())
                add("implementation", libs.findLibrary("androidx.compose.material3").get())
                add("implementation", libs.findLibrary("androidx.compose.runtime").get())
                add("implementation", libs.findLibrary("androidx.compose.ui.tooling").get())
                add("implementation", libs.findLibrary("androidx.compose.ui.tooling.preview").get())
                add("implementation", project(":common:designsystem"))
                add("ksp", libs.findLibrary("compose.destinations.ksp").get())

                //add("implementation", libs.findDependency("androidx.activity.activityCompose").get())
                //add("implementation", libs.findDependency("hilt.navigationCompose").get())
//                add("implementation", project(":analytics"))
//                add("implementation", project(":common:framework:flows"))
//                add("implementation", project(":common:framework:mvi"))
//                add("implementation", project(":common:framework:navigation"))
//                add("implementation", project(":common:resources"))
//                add("implementation", project(":common:ui"))
//                add("implementation", project(":common:wrappers:dispatchers"))
//                add("implementation", project(":data:common"))
//                add("testImplementation", project(":test"))
//                add("testImplementation", libs.findBundle("unitTest").get())
            }
        }
    }
}

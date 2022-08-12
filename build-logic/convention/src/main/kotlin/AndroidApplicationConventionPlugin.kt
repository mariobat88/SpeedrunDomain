import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.speedrun.domain.app
import com.speedrun.domain.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

@Suppress("unused", "UnstableApiUsage")
class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("com.google.devtools.ksp")
            }

            extensions.configure<BaseAppModuleExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = app.targetSdk

                val libs = extensions.getByType(VersionCatalogsExtension::class).named("libs")

                dependencies {
                    add("implementation", libs.findLibrary("ksp").get())
                }
            }
        }
    }
}

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.speedrun.domain.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("speedrun.domain.android.application")
            }

            extensions.configure<BaseAppModuleExtension> {
                configureAndroidCompose(this)
            }
        }
    }
}

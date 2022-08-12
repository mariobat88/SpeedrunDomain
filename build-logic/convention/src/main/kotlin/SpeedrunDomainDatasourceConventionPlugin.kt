import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

@Suppress("unused")
class SpeedrunDomainDatasourceConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("speedrun.domain.android.library")
                apply("speedrun.domain.android.hilt")
            }

            dependencies {
                add("implementation", project(":common:wrapper:dispatchers"))
            }
        }
    }
}

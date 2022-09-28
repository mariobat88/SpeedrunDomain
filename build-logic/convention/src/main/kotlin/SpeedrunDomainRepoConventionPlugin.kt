import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class SpeedrunDomainRepoConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("java-library")
                apply("org.jetbrains.kotlin.jvm")
            }

            dependencies {
                add("implementation", project(":data:common"))
                add("implementation", project(":data:repo:common"))
            }
        }
    }
}

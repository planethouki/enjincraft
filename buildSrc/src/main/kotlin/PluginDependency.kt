import org.gradle.api.plugins.ObjectConfigurationAction
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

class PluginDependency(id: String, version: String) {
    val id = id
    val version = version
}

fun PluginDependenciesSpec.id(
        dependency: PluginDependency,
        apply: Boolean = true
): PluginDependencySpec = id(dependency.id).version(dependency.version).apply(apply)

fun ObjectConfigurationAction.plugin(
        dependency: PluginDependency
): ObjectConfigurationAction = plugin(dependency.id)

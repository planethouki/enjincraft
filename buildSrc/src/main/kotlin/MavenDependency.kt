import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

class MavenDependency(group: String, id: String, version: String, classifier: String? = null) {
    internal val group: String = group
    internal val id: String = id
    internal val version: String = version
    private val classifier: String? = classifier?.trim()

    override fun toString(): String {
        val identifier = "${group}:${id}:${version}"
        return if (classifier != null && classifier.isNotEmpty()) "${identifier}:${classifier}" else identifier
    }

    fun projectId(): String {
        return "${group}:${id}"
    }
}

fun DependencyHandler.compileOnly(dependency: MavenDependency): Dependency? {
    return add("compileOnly", dependency.toString())
}

fun DependencyHandler.implementation(dependency: MavenDependency): Dependency? {
    return add("implementation", dependency.toString())
}

fun DependencyHandler.api(dependency: MavenDependency): Dependency? {
    return add("api", dependency.toString())
}

fun DependencyHandler.runtimeOnly(dependency: MavenDependency): Dependency? {
    return add("runtimeOnly", dependency.toString())
}

fun DependencyHandler.shadow(dependency: MavenDependency): Dependency? {
    return add("shadow", dependency.toString())
}

fun DependencyHandler.annotationProcessor(dependency: MavenDependency): Dependency? {
    return add("annotationProcessor", dependency.toString())
}

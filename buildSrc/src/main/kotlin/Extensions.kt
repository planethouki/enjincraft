import org.gradle.api.JavaVersion
import org.gradle.api.tasks.compile.JavaCompile
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

fun JavaCompile.setSourceCompatibility(version: JavaVersion) {
    sourceCompatibility = version.toString()
}

fun JavaCompile.setTargetCompatibility(version: JavaVersion) {
    targetCompatibility = version.toString()
}

fun KotlinJvmOptions.setJvmTarget(version: JavaVersion) {
    jvmTarget = version.toString()
}

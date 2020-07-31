import com.bmuschko.gradle.nexus.ExtraArchivePluginExtension
import com.bmuschko.gradle.nexus.NexusPluginExtension
import io.codearte.gradle.nexus.NexusStagingExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    val local = JProps()
    val file: File = project.rootProject.file("local.properties")
    if (file.exists()) local.load(file.inputStream())
    extra.set("local", local)
}

plugins {
    `java-library`
    kotlin("jvm")
    id(BuildPlugins.lombok, false)
    id(BuildPlugins.shadow, false)
    id(BuildPlugins.nexus, false)
    id(BuildPlugins.nexus_staging)
}

allprojects {
    group = "com.enjin.enjincraft"
    version = "1.1.0-SNAPSHOT"

    repositories {
        mavenLocal()
        mavenCentral()
        maven("https://oss.sonatype.org/content/groups/public/")
        maven("https://jitpack.io")
    }
}

subprojects {
    apply {
        plugin("kotlin")
        plugin(BuildPlugins.lombok)
        plugin(BuildPlugins.shadow)
        plugin(BuildPlugins.nexus)
    }

    dependencies {
//        implementation(kotlin("stdlib", Kotlin.version))
        compileOnly(Libraries.sentry)
    }

    repositories {
        mavenCentral()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            setJvmTarget(JavaVersion.VERSION_1_8)
        }
    }

    tasks.withType<JavaCompile> {
        setSourceCompatibility(JavaVersion.VERSION_1_8)
        setTargetCompatibility(JavaVersion.VERSION_1_8)
    }

    task<Jar>("javadocJar") {
        dependsOn("javadoc")
        archiveClassifier.set("javadoc")
        from(tasks.javadoc)
    }

    task<Jar>("sourcesJar") {
        archiveClassifier.set("sources")
        sourceSets {
            main {
                from(allJava)
            }
        }
    }

    artifacts {
        archives(tasks.named("javadocJar"))
        archives(tasks.named("sourcesJar"))
        archives(tasks.named("shadowJar"))
    }

    configure<ExtraArchivePluginExtension> {
        sources = false
        javadoc = false
        tests = false
    }

    configure<NexusPluginExtension> {
        sign = false
        repositoryUrl = "https://oss.sonatype.org/service/local/staging/deploy/maven2/"
        snapshotRepositoryUrl = "https://oss.sonatype.org/content/repositories/snapshots/"
    }

    // Fixes an issue where some libraries could not be resolved.
    configurations.named("shadow") {
        attributes {
            attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage.JAVA_API))
            attribute(Category.CATEGORY_ATTRIBUTE, objects.named(Category.LIBRARY))
        }
    }
}

configure<NexusStagingExtension> {
    val local: JProps by extra
    username = local.getProperty("nexusUsername")
    password = local.getProperty("nexusPassword")
}

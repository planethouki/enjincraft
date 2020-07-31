plugins {
    `kotlin-dsl`
}

dependencies {
    gradleApi()
    api(kotlin("gradle-plugin", "1.3.72"))
}

repositories {
    mavenCentral()
    jcenter()
}

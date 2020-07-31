import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.apache.tools.ant.filters.ReplaceTokens

repositories {
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("http://repo.extendedclip.com/content/repositories/placeholderapi/")
    maven("https://repo.codemc.org/repository/maven-public/")
}

dependencies {
    shadow(Libraries.Spigot.Api.`1_13_2`)
    shadow(Libraries.Spigot.placeholder_api)
    shadow(Libraries.sqlite)
    shadow(Libraries.Kyori.api)
    shadow(Libraries.Kyori.legacy_serializer)
    shadow(Libraries.Kyori.bukkit_adapter)
    shadow(Libraries.pusher)
    shadow(Libraries.Square.OkHttp.core)
    shadow(Libraries.Square.OkHttp.url)
    shadow(Libraries.Square.Retrofit.core)
    shadow(Libraries.Square.Retrofit.gson)

    implementation(Libraries.Enjin.spigot_commons)
    implementation(Libraries.Spigot.nbt_api)

    api(Libraries.Enjin.blockchain_sdk)

    // Set to compileOnly to resolve issue with another project including a different version of GSON.
    compileOnly(Libraries.gson)
}

tasks.withType<Jar> {
    archiveBaseName.set("EnjinCraft-Spigot")
}

tasks.withType<ShadowJar> {
    dependencies {
        // Exclude GSON as this is fetched at runtime.
        exclude(dependency(Libraries.gson.projectId()))
    }

    val libsPackage = "com.enjin.enjincraft.spigot.libs"
    relocate("de.tr7zw", "${libsPackage}.tr7zw")
    relocate("kotlin", "${libsPackage}.kotlin")
    relocate("okhttp3", "${libsPackage}.square.okhttp3")
    relocate("okio", "${libsPackage}.square.okio")
    relocate("retrofit2", "${libsPackage}.square.retrofit2")
    relocate("org.intellij", "${libsPackage}.intellij")
    relocate("org.jetbrains", "${libsPackage}.jetbrains")
    relocate("org.slf4j", "${libsPackage}.slf4j")
    relocate("org.java_websocket", "${libsPackage}.java_websocket")
    relocate("com.pusher", "${libsPackage}.pusher")
}


tasks.withType<ProcessResources> {
    filter<ReplaceTokens>("tokens" to hashMapOf(
        "version" to project.version.toString()
    ))
}

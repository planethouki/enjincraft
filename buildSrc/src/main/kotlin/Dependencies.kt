object Kotlin {
    const val version = "1.3.72"
}

object BuildPlugins {
    val shadow = PluginDependency("com.github.johnrengelman.shadow", "6.0.0")
    val nexus = PluginDependency("com.bmuschko.nexus", "2.3.1")
    val nexus_staging = PluginDependency("io.codearte.nexus-staging", "0.21.0")
    val git_publish = PluginDependency("org.ajoberstar.git-publish", "2.1.1")
    val lombok = PluginDependency("io.freefair.lombok", "5.1.0")
}

object Libraries {
    object Enjin {
        val spigot_commons = MavenDependency("com.enjin", "spigot-commons", "1.0.0")
        val blockchain_sdk = MavenDependency("com.enjin", "blockchain-sdk", "1.0.3r1")
    }

    object Spigot {
        object Api {
            val `1_13_2` = MavenDependency("org.spigotmc", "spigot-api", "1.13.2-R0.1-SNAPSHOT")
        }

        val placeholder_api = MavenDependency("me.clip", "placeholderapi", "2.10.3")
        val nbt_api = MavenDependency("de.tr7zw", "item-nbt-api", "2.4.1")
    }

    object Kyori {
        val api = MavenDependency("net.kyori", "text-api", "3.0.2")
        val legacy_serializer = MavenDependency(api.group, "text-serializer-legacy", api.version)
        val bukkit_adapter = MavenDependency(api.group, "text-adapter-bukkit", "3.0.3")
    }

    object Square {
        object OkHttp {
            val core = MavenDependency("com.squareup.okhttp3", "okhttp", "4.8.0")
            val logging = MavenDependency(core.group, "logging-interceptor", core.version)
            val url = MavenDependency(core.group, "okhttp-urlconnection", core.version)
        }
        object Retrofit {
            val core = MavenDependency("com.squareup.retrofit2", "retrofit", "2.7.1")
            val gson = MavenDependency(core.group, "converter-gson", core.version)
        }
    }

    val gson = MavenDependency("com.google.code.gson", "gson", "2.8.0")
    val sqlite = MavenDependency("org.xerial", "sqlite-jdbc", "3.28.0")
    val sentry = MavenDependency("io.sentry", "sentry", "1.7.27")
    val pusher = MavenDependency("com.pusher", "pusher-java-client", "2.0.1")
    val opencsv = MavenDependency("com.opencsv", "opencsv", "5.1")
}

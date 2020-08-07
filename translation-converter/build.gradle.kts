plugins {
    application
}

dependencies {
    implementation(Libraries.opencsv)
}

application {
    mainClassName = "com.enjin.enjincraft.translations.FileConverter"
}

tasks.named<JavaExec>("run") {
    doFirst {
        val file = File(projectDir, "/build/translation")
        file.mkdirs()
        workingDir = file
    }
}

import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "com.xdd.browse"
version = "1.0-SNAPSHOT"
val jcefVersion = project.property("jcefVersion") as String

kotlin {
    jvm {
        jvmToolchain(17)
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(project(":common"))
                implementation(compose.desktop.currentOs)
                // https://github.com/jcefmaven/jcefmaven/releases
                implementation("me.friwi:jcefmaven:$jcefVersion")
                // https://kotlinlang.org/api/kotlinx.coroutines/
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.6.4")
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "EasyBrowse"
            packageVersion = "1.0.0"
        }
        buildTypes.release.proguard {
            isEnabled.set(true)
            configurationFiles.from("proguard-rules.pro")
        }
        jvmArgs.apply {
            add("--add-opens=java.desktop/java.awt.peer=ALL-UNNAMED")
            add("--add-opens=java.desktop/sun.awt=ALL-UNNAMED")
            add("--add-opens=java.desktop/sun.lwawt=ALL-UNNAMED")
            add("--add-opens=java.desktop/sun.lwawt.macosx=ALL-UNNAMED")
            add("--add-exports=java.base/java.lang=ALL-UNNAMED")
            add("--add-exports=java.desktop/sun.awt=ALL-UNNAMED")
            add("--add-exports=java.desktop/sun.java2d=ALL-UNNAMED")
            add("--add-exports=java.desktop/sun.awt=ALL-UNNAMED")
        }
    }
}


val generatePropertiesFile by tasks.register("generatePropertiesFile") {
    val propertiesFile = file("$buildDir/generated/resources/gradle.properties")
    outputs.file(propertiesFile)

    doLast {
        propertiesFile.parentFile.mkdirs()
        propertiesFile.writeText("jcefVersion=$jcefVersion")
    }
}

sourceSets["main"].resources.srcDir("$buildDir/generated/resources")
tasks.named("compileKotlinJvm").configure {
    dependsOn(generatePropertiesFile)
}
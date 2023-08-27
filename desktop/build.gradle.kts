import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization")
}

group = "com.xdd.browse"
version = "1.0-SNAPSHOT"

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
                implementation(Libs.jcef.toDependence())
                implementation(Libs.coroutines.toDependence())
                implementation(Libs.serialization.toDependence())
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
        propertiesFile.writeText("jcefVersion=${Libs.jcef.version}")
    }
}

sourceSets["main"].resources.srcDir("$buildDir/generated/resources")
tasks.named("compileKotlinJvm").configure {
    mustRunAfter(generatePropertiesFile)
}
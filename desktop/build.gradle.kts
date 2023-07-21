import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    // https://github.com/openjfx/javafx-gradle-plugin
    id("org.openjfx.javafxplugin") version "0.0.14"
}

javafx {
    modules("javafx.controls", "javafx.fxml", "javafx.web", "javafx.swing")
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
    }
}

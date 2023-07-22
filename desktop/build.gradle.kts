import android.databinding.tool.ext.fieldSpec
import com.squareup.javapoet.TypeName
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.fir.declarations.builder.buildField

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
        jvmArgs.apply {
            add("--add-opens=java.desktop/java.awt.peer=ALL-UNNAMED")
            add("--add-opens=java.desktop/sun.awt=ALL-UNNAMED")
            add("--add-opens=java.desktop/sun.lwawt=ALL-UNNAMED")
            add("--add-opens=java.desktop/sun.lwawt.macosx=ALL-UNNAMED")
            add("--add-exports=java.base/java.lang=ALL-UNNAMED")
            add("--add-exports=java.desktop/sun.awt=ALL-UNNAMED")
            add("--add-exports=java.desktop/sun.java2d=ALL-UNNAMED")
        }
    }
}




val generatePropertiesFile by tasks.register("generatePropertiesFile") {
    val propertiesFile = file("$buildDir/generated/gradle.properties")
    outputs.file(propertiesFile)

    doLast {
        propertiesFile.parentFile.mkdirs()
        propertiesFile.writeText("jcefVersion=$jcefVersion")
    }
}

sourceSets.main.get().resources.srcDir("$buildDir/generated")
tasks.named("compileKotlinJvm").configure {
    dependsOn(generatePropertiesFile)
}
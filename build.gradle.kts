group = "com.xdd.browse"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

buildscript {
    dependencies {
        // 同kotlin版本
        classpath(kotlin("serialization", version = Libs.kotlin.version))
    }
}

plugins {
    kotlin("multiplatform").version(Libs.kotlin.version).apply(false)
    kotlin("android").version(Libs.kotlin.version).apply(false)
    id("com.android.application").version(Libs.agp.version).apply(false)
    id("com.android.library").version(Libs.agp.version).apply(false)
    id("org.jetbrains.compose").version(Libs.compose.version).apply(false)
}

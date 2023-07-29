plugins {
    `java-gradle-plugin`
    kotlin("jvm") version "1.6.0"
}

group = "com.xdd.browse.version"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
}

gradlePlugin {
    plugins {
        create("VersionPlugin") {
            id = "browse-version"
            implementationClass = "com.xdd.browse.VersionPlugin"
        }
    }
}
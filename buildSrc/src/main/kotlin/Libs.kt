object Libs {
    val kotlin = Lib(
        version = "1.9.0",
        info = "https://kotlinlang.org/docs/whatsnew19.html",
    )

    val agp = Lib(
        version = "8.0.0",
        info = "https://mvnrepository.com/artifact/com.android.application/com.android.application.gradle.plugin",
    )

    val compose = Lib(
        version = "1.5.0",
        info = "https://github.com/JetBrains/compose-multiplatform",
    )

    val jcef = Lib(
        version = "110.0.25.1",
        dependence = "me.friwi:jcefmaven:",
        info = " https://github.com/jcefmaven/jcefmaven/releases",
    )

    val coroutines = Lib(
        version = "1.6.4",
        dependence = "org.jetbrains.kotlinx:kotlinx-coroutines-swing:",
        info = "https://kotlinlang.org/api/kotlinx.coroutines/",
    )

    val serialization = Lib(
        version = "1.6.0-RC",
        dependence = "org.jetbrains.kotlinx:kotlinx-serialization-json:",
        info = "https://github.com/Kotlin/kotlinx.serialization",
    )

    data class Lib(
        val version: String,
        val dependence: String = "",
        val info: String = "",
    ) {
        fun toDependence(): String = "${dependence}${version}"

    }
}
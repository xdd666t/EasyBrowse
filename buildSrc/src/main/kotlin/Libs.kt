object Libs {
    val kotlin = Lib(
        version = "1.9.0",
        info = "https://kotlinlang.org/docs/whatsnew19.html",
    )

    val agp = Lib(
        version = "7.4.2",
        info = "",
    )

    val compose = Lib(
        version = "1.4.3",
        info = "",
    )

    val jcef = Lib(
        version = "110.0.25",
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

    class Lib(
        val version: String,
        val dependence: String = "",
        val info: String = "",
    ) {
        fun toDependence(): String {
            return "${dependence}${version}"
        }
    }
}
package app.constant

import java.util.*


object GradleInfo {
    private val properties = Properties().apply {
        ClassLoader.getSystemResourceAsStream("gradle.properties")?.use { load(it) }
    }

    val jcefVersion: String by lazy {
        (properties["jcefVersion"] as String?) ?: "default"
    }
}
package app.constant

object PathInfo {
    val userPath: String by lazy {
        System.getProperty("user.home") ?: ""
    }

    val cachePath = "$userPath/EasyBrowse"

    val jcefPrefix: String by lazy {
        "easy_browse_jcef_"
    }

    val jcefPath: String by lazy {
        "$cachePath/$jcefPrefix${GradleInfo.jcefVersion}"
    }
}
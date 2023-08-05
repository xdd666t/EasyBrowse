package app.constant

object PathInfo {
    private val userPath: String by lazy {
        System.getProperty("user.home") ?: ""
    }

    private val cachePath = "$userPath/EasyBrowse"

    private val jcefPrefix: String by lazy {
        "easy_browse_jcef_"
    }

    val jcefPath: String by lazy {
        "$cachePath/$jcefPrefix${GradleInfo.jcefVersion}"
    }

    val browseInfo: String = "$cachePath/data/browse.json"
}
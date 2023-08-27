package app.constant

object PathInfo {
    private val userPath = System.getProperty("user.home") ?: ""

    private val cachePath = "$userPath/EasyBrowse"

    private val jcefPrefix = "easy_browse_jcef_"

    val jcefPath = "$cachePath/$jcefPrefix${GradleInfo.jcefVersion}"

    val browseInfo = "$cachePath/data/browse.json"
}
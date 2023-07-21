package web

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import me.friwi.jcefmaven.CefAppBuilder
import me.friwi.jcefmaven.MavenCefAppHandlerAdapter
import org.cef.CefApp
import org.cef.browser.CefBrowser
import java.io.File
import java.util.logging.Level
import java.util.logging.Logger

object ChromiumWrap {
    private var mInitUrl: String = ""
    private lateinit var mCefApp: CefApp
    private val mCefBrowser: CefBrowser by lazy {
        mCefApp.createClient().createBrowser(mInitUrl, false, false)
    }

    init {
        val builder = CefAppBuilder().apply {
            val userPath = System.getProperty("user.home") ?: ""
            setInstallDir(File(userPath, "jcef-bundle"))
            setProgressHandler { state, percent ->
                Logger.getLogger("ChromiumWrap").log(
                    Level.INFO,
                    state.toString() + " |> " + if (percent == -1f) "In progress..." else percent
                )

            }
            setAppHandler(object : MavenCefAppHandlerAdapter() {
                override fun stateHasChanged(state: CefApp.CefAppState?) {}
            })
        }



        mCefApp = builder.build()
    }

    @Composable
    fun Web(initUrl: String = "") {
        mInitUrl = initUrl
        SwingPanel(
            modifier = Modifier.fillMaxSize(),
            factory = { mCefBrowser.uiComponent }
        )
    }

    fun loadUrl(url: String) {
        mCefBrowser.loadURL(url)
    }
}
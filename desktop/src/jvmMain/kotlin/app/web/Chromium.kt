package app.web

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import androidx.compose.ui.unit.dp
import app.constant.PathInfo
import kotlinx.coroutines.*
import me.friwi.jcefmaven.CefAppBuilder
import me.friwi.jcefmaven.EnumProgress
import me.friwi.jcefmaven.MavenCefAppHandlerAdapter
import org.cef.CefApp
import org.cef.browser.CefBrowser
import view.FancyProgressBar
import java.io.File
import java.util.logging.Level
import java.util.logging.Logger

private enum class WebStatus {
    Hide, Visible, Install,
}

object ChromiumWrap {
    private var mInitUrl: String = ""
    private lateinit var mCefApp: CefApp
    private val mCefBrowser: CefBrowser by lazy {
        mCefApp.createClient().createBrowser(mInitUrl, false, false)
    }
    private val mProgress = mutableStateOf(0f)
    private val mInstallMsg = mutableStateOf("")
    private val mWebStatus = mutableStateOf(WebStatus.Hide)
    private val mScope = CoroutineScope(Dispatchers.Default)

    init {
        val builder = CefAppBuilder().apply {
            setInstallDir(File(PathInfo.jcefPath))
            setProgressHandler { state, percent ->
                Logger.getLogger("ChromiumWrap").log(
                    Level.INFO, "$state |> $percent"
                )
                runBlocking {
                    withContext(Dispatchers.Main) {
                        when (state) {
                            EnumProgress.DOWNLOADING -> {
                                mWebStatus.value = WebStatus.Install
                                mProgress.value = (percent * 0.01).toFloat()
                                mInstallMsg.value = "正在下载Java Chromium Embedded Framework..."
                            }
                            EnumProgress.EXTRACTING -> {
                                mWebStatus.value = WebStatus.Install
                                mInstallMsg.value = "解压中..."
                            }
                            EnumProgress.INSTALL -> {
                                mWebStatus.value = WebStatus.Install
                                mInstallMsg.value = "安装中..."
                            }

                            EnumProgress.INITIALIZED -> mWebStatus.value = WebStatus.Visible
                            else -> {}
                        }
                    }
                }
            }
            setAppHandler(object : MavenCefAppHandlerAdapter() {
                override fun stateHasChanged(state: CefApp.CefAppState?) {}
            })
        }

        mScope.launch {
            mCefApp = builder.build()
        }
    }

    @Composable
    fun Web(initUrl: String = "") {
        mInitUrl = initUrl
        if (mWebStatus.value == WebStatus.Visible) {
            SwingPanel(modifier = Modifier.fillMaxSize(), factory = { mCefBrowser.uiComponent })
        } else if (mWebStatus.value == WebStatus.Install) {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(horizontal = 100.dp)) {
                    Text(mInstallMsg.value)
                    FancyProgressBar(
                        progress = mProgress.value,
                        modifier = Modifier.padding(top = 30.dp, bottom = 100.dp),
                    )
                }
            }
        }
    }

    fun loadUrl(url: String) {
        mCefBrowser.loadURL(url)
    }
}
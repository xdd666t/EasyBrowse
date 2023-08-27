package app.web

import androidx.compose.runtime.mutableStateOf
import app.constant.PathInfo
import kotlinx.coroutines.*
import me.friwi.jcefmaven.CefAppBuilder
import me.friwi.jcefmaven.EnumProgress
import me.friwi.jcefmaven.MavenCefAppHandlerAdapter
import org.cef.CefApp
import org.cef.CefClient
import java.io.File
import java.util.logging.Level
import java.util.logging.Logger

enum class WebStatus {
    Hide, Visible, Install,
}

object CefAppKit {
    private lateinit var mCefApp: CefApp
    private val mScope = CoroutineScope(Dispatchers.Default)

    val mCefClient: CefClient by lazy {
        mCefApp.createClient()
    }
    val mProgress = mutableStateOf(0f)
    val mInstallMsg = mutableStateOf("")
    val mWebStatus = mutableStateOf(WebStatus.Hide)


    init {
        val builder = CefAppBuilder().apply {
            setInstallDir(File(PathInfo.jcefPath))
            cefSettings.apply {
                background_color = ColorType(0xff, 0xff, 0xff, 0xff)
            }
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
}
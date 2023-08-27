package app.web

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.cef.browser.CefBrowser
import view.FancyProgressBar


class Chromium {
    private var mCefBrowser: CefBrowser? = null

    @Composable
    fun createWeb(initUrl: String = "") {
        if (CefAppKit.mWebStatus.value == WebStatus.Visible) {
            if (mCefBrowser == null) {
                mCefBrowser = CefAppKit.mCefClient.createBrowser(
                    initUrl,
                    false,
                    false,
                )
            }
            SwingPanel(
                modifier = Modifier.fillMaxSize().background(color = Color.White),
                factory = { mCefBrowser!!.uiComponent }
            )
        } else if (CefAppKit.mWebStatus.value == WebStatus.Install) {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(horizontal = 100.dp)) {
                    Text(CefAppKit.mInstallMsg.value)
                    FancyProgressBar(
                        progress = CefAppKit.mProgress.value,
                        modifier = Modifier.padding(top = 30.dp, bottom = 100.dp),
                    )
                }
            }
        }
    }
}
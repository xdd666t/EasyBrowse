package app.web

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import javafx.application.Application
import javafx.application.Platform
import javafx.embed.swing.JFXPanel
import javafx.scene.Scene
import javafx.scene.layout.StackPane
import javafx.scene.web.WebView
import javafx.stage.Stage
import java.awt.Component

fun main() {
    Application.launch(MainApplication::class.java)

//    application {
//        Window(onCloseRequest = ::exitApplication) {
//            Column {
//                Text("11111111")
//                ContentView()
//            }
//        }
//    }
}

class MainApplication : Application() {
    override fun start(stage: Stage) {
        stage.title = "JavaFX WebView Example"
        val webView = WebView()
        webView.engine.load("https://www.baidu.com/")
        val root = StackPane()
        root.children.add(webView)
        val scene = Scene(root)
        stage.scene = scene
        stage.show()
    }
}


@Composable
fun ContentView() {
    Box(modifier = Modifier.fillMaxSize()) {
        SwingPanel(modifier = Modifier.fillMaxSize(), factory = {
            createJFXPanelWithWebView("https://poe.com/ChatGPT")
        })
    }
}

fun createJFXPanelWithWebView(url: String): Component {
    val jfxPanel = JFXPanel()
    Platform.runLater {
        val webView = WebView()
        val webEngine = webView.engine
        webEngine.load(url)
        val scene = Scene(webView)
        jfxPanel.scene = scene
    }
    return jfxPanel
}
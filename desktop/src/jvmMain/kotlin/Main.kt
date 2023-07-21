import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import web.ChromiumWrap


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        Column {
            Button(onClick = {
                ChromiumWrap.loadUrl("https://www.youtube.com/watch?v=6x_QMbtt6gw")
            }) {
                Text("text")
            }
            ChromiumWrap.Web(initUrl = "https://www.youtube.com/watch?v=6x_QMbtt6gw")
        }
    }
}

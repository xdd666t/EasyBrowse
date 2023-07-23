package module.main


import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import app.web.ChromiumWrap
import module.main.view.SideNavigation

fun MainView() = application {
    Window(
        state = rememberWindowState(
            position = WindowPosition.Aligned(Alignment.Center),
            size = DpSize(1200.dp, 800.dp),
        ),
        onCloseRequest = ::exitApplication,
        title = "EasyBrowser",
    ) {
        SideNavigation(
            data = listOf("111", "222", "333", "34343333333333333333")
        ) {
            ChromiumWrap.Web(initUrl = "https://www.youtube.com/watch?v=6x_QMbtt6gw")
        }
    }
}


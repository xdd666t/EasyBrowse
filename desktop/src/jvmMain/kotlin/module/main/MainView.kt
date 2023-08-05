package module.main


import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import module.main.view.SideNavigation
import module.main.view.WebScope

val viewModel = MainViewModel()

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
            data = viewModel,
            onBrowseItem = { index ->
                viewModel.switchUrl(index)
            },
            onFunction = {index ->
                viewModel.onFunction(index)
            }
        ) {
            WebScope(
                sideItems = viewModel.sideItems,
                selectIndex = viewModel.selectIndex.value,
            )
        }
    }
}


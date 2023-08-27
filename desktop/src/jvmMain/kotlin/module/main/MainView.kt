package module.main


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import app.kit.rememberScope
import module.main.view.SideNavigation

lateinit var mainViewModel: MainViewModel

@OptIn(ExperimentalFoundationApi::class)
fun MainView() = application {
    Window(
        state = rememberWindowState(
            position = WindowPosition.Aligned(Alignment.Center),
            size = DpSize(1200.dp, 800.dp),
        ),
        onCloseRequest = ::exitApplication,
        title = "EasyBrowser",
    ) {
        rememberScope = rememberCoroutineScope()
        mainViewModel = MainViewModel()
        val state = mainViewModel.state

        SideNavigation(
            data = mainViewModel,
            onBrowseItem = { index -> mainViewModel.switchBrowse(index) },
            onFunction = { index -> mainViewModel.onFunction(index) }
        ) {
            when (state.switchType.value) {
                // 浏览区域
                SwitchType.browse -> {
                    VerticalPager(
                        pageCount = state.browseItems.size,
                        state = state.pagerState,
                        modifier = Modifier.background(Color.White),
                    ) { index ->
                        val item = state.browseItems[index]
                        item.chromium?.createWeb(initUrl = item.url)
                    }
                }
                // 功能区域
                SwitchType.function -> {
                    VerticalPager(
                        pageCount = state.functionItems.size,
                        state = state.pagerState,
                        modifier = Modifier.background(Color.White),
                    ) { index -> state.functionItems[index].page?.invoke() }
                }
            }
        }
    }
}


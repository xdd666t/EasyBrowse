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
        val viewModel = MainViewModel()
        val state = viewModel.state

        SideNavigation(
            data = viewModel,
            onBrowseItem = { index -> viewModel.switchBrowse(index) },
            onFunction = { index -> viewModel.onFunction(index) }
        ) {
            when (state.switchType.value) {
                // 浏览区域
                SwitchType.browse -> {
                    VerticalPager(
                        pageCount = state.sideItems.size, state = state.pagerState,
                        modifier = Modifier.background(Color.White),
                    ) { index ->
                        state.sideItems[index].chromium?.Web()
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


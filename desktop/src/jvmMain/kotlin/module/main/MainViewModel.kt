package module.main

import androidx.compose.foundation.ExperimentalFoundationApi
import app.kit.FileKit
import app.kit.rememberScope
import app.web.Chromium
import kotlinx.coroutines.launch
import java.net.URL

class MainViewModel {
    val state = MainState()

    init {
        state.sideItems.addAll(FileKit.readBrowseInfo())
        state.sideItems.forEach { item ->
            val url = URL(item.url)
            item.iconUrl = "${url.protocol}://${url.host}/favicon.ico"
            item.chromium = Chromium(initUrl = item.url)
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    fun switchBrowse(index: Int) {
        if (state.switchType.value != SwitchType.browse) {
            state.switchType.value = SwitchType.browse
        }
        rememberScope.launch {
            state.pagerState.scrollToPage(index)
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    fun onFunction(index: Int) {
        if (state.switchType.value != SwitchType.function) {
            state.switchType.value = SwitchType.function
        }
        rememberScope.launch {
            state.pagerState.scrollToPage(index)
        }
    }
}
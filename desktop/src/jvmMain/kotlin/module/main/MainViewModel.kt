package module.main

import androidx.compose.foundation.ExperimentalFoundationApi
import app.kit.FileKit
import app.kit.rememberScope
import kotlinx.coroutines.launch
import model.BrowseModel
import java.net.URL

class MainViewModel {
    val state = MainState()

    init {
        refreshBrowse()
    }

    fun refreshBrowse() {
        val originList = FileKit.readBrowseInfo()
        val diffList = mutableListOf<BrowseModel>()
        originList.forEach { item ->
            var needAdd = true
            state.browseItems.forEach { subItem ->
                if (item.url == subItem.url) {
                    needAdd = false
                }
            }
            if (needAdd) {
                diffList.add(item)
            }
        }
        diffList.forEach { item ->
            val url = URL(item.url)
            item.iconUrl = "${url.protocol}://${url.host}/favicon.ico"
//            item.chromium = Chromium(initUrl = item.url)
        }

        state.browseItems.addAll(diffList)
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
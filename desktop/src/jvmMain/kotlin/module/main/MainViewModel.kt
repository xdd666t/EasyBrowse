package module.main

import androidx.compose.foundation.ExperimentalFoundationApi
import app.kit.FileKit
import app.kit.rememberScope
import app.web.Chromium
import kotlinx.coroutines.launch
import model.BrowseModel

class MainViewModel {
    val state = MainState()

    init {
        refreshBrowse()
        initSelected()
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
            item.chromium = Chromium()
        }

        state.browseItems.addAll(diffList)
    }

    @OptIn(ExperimentalFoundationApi::class)
    fun initSelected() {
        var selectIndex = 0
        val items = state.browseItems
        items.forEachIndexed { index, browseModel ->
            if (items[index].selected) {
                selectIndex = index
            }
        }
        state.selectedIndex.value = selectIndex
        rememberScope.launch {
            state.browsePagerState.scrollToPage(selectIndex)
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    fun switchBrowse(index: Int) {
        val items = state.browseItems
        for (item in items) {
            item.selected = false
        }
        items[index].selected = true
        state.selectedIndex.value = index

        if (state.switchType.value != SwitchType.browse) {
            state.switchType.value = SwitchType.browse
        }
        rememberScope.launch {
            state.browsePagerState.scrollToPage(index)
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    fun onFunction(index: Int) {
        if (state.switchType.value != SwitchType.function) {
            state.switchType.value = SwitchType.function
        }
        rememberScope.launch {
            state.functionPagerState.scrollToPage(index)
        }
    }
}
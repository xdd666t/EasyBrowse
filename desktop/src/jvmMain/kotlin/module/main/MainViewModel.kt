package module.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.mutableStateOf
import app.kit.FileKit
import app.web.Chromium
import model.FunctionModel
import model.SideModel
import java.net.URL

class MainViewModel {
    val sideItems: MutableList<SideModel> = mutableListOf()
    val groupItems: List<FunctionModel> = mutableListOf(
        FunctionModel(imageVector = Icons.Filled.List, title = "List"),
        FunctionModel(imageVector = Icons.Filled.Call, title = "Call"),
        FunctionModel(imageVector = Icons.Filled.Check, title = "Check"),
        FunctionModel(imageVector = Icons.Filled.MoreVert, title = "MoreVert"),
    )
    val functionItems: List<FunctionModel> = mutableListOf(
        FunctionModel(imageVector = Icons.Filled.Add, title = "Add"),
        FunctionModel(imageVector = Icons.Filled.Settings, title = "Settings"),
    )
    var selectIndex = mutableStateOf(0)

    init {
        sideItems.addAll(FileKit.readBrowseInfo())
        sideItems.forEach { item ->
            val url = URL(item.url)
            item.iconUrl = "${url.protocol}://${url.host}/favicon.ico"
            item.chromium = Chromium(initUrl = item.url)
        }
    }

    fun switchUrl(index: Int) {
        selectIndex.value = index
    }

    fun onFunction(index: Int) {
    }
}
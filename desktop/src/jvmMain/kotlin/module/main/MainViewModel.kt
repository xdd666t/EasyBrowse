package module.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.mutableStateOf
import app.web.Chromium
import module.main.model.FunctionModel
import module.main.model.SideModel
import java.net.URL

class MainViewModel {
    val sideItems: List<SideModel> = mutableListOf(
        SideModel(url = "https://www.youtube.com/watch?v=Hm7K9xJymiM"),
        SideModel(url = "https://github.com/xdd666t"),
        SideModel(url = "https://juejin.cn/user/2840793776393847"),
        SideModel(url = "https://poe.com/ChatGPT"),
        SideModel(url = "https://yiyan.baidu.com/"),
    )
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
        sideItems.forEach { item ->
            val url = URL(item.url)
            item.iconUrl = "${url.protocol}://${url.host}/favicon.ico"
            item.chromium = Chromium(initUrl = item.url)
        }
    }

    fun switchUrl(index: Int) {
        selectIndex.value = index
    }
}
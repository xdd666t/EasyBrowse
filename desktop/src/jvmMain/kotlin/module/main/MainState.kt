package module.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.mutableStateOf
import model.FunctionModel
import model.SideModel
import module.expansion.ExpansionView
import module.setting.SettingView

enum class SwitchType {
    browse,
    function,
}

class MainState {
    val sideItems: MutableList<SideModel> = mutableListOf()
    val groupItems: List<FunctionModel> = mutableListOf(
        FunctionModel(imageVector = Icons.Filled.List, title = "List"),
        FunctionModel(imageVector = Icons.Filled.Call, title = "Call"),
        FunctionModel(imageVector = Icons.Filled.Check, title = "Check"),
        FunctionModel(imageVector = Icons.Filled.MoreVert, title = "MoreVert"),
    )
    val functionItems: List<FunctionModel> = mutableListOf(
        FunctionModel(
            imageVector = Icons.Filled.Add,
            title = "Add",
            page = {
                ExpansionView()
            }
        ),
        FunctionModel(
            imageVector = Icons.Filled.Settings,
            title = "Settings",
            page = {
                SettingView()
            }
        ),
    )

    @OptIn(ExperimentalFoundationApi::class)
    var pagerState = PagerState()

    var switchType = mutableStateOf(SwitchType.browse)
}
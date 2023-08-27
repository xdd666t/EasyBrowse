package module.expansion

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import app.kit.FileKit
import model.BrowseModel
import module.main.mainViewModel


class ExpansionViewModel {
    val addInfo = AddInfoModel()

    fun onSave() {
        if (addInfo.title.value == "" || addInfo.url.value == "") {
            return
        }

        FileKit.writeBrowseInfo(
            BrowseModel(
                title = addInfo.title.value,
                url = addInfo.url.value
            )
        )
        mainViewModel.refreshBrowse()
        addInfo.title.value = ""
        addInfo.url.value = ""
    }
}

data class AddInfoModel(
    val title: MutableState<String> = mutableStateOf(""),
    val url: MutableState<String> = mutableStateOf(""),
)